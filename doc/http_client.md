# HttpClient

```java
HttpClient httpClient = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_1_1)
    .connectTimeout(Duration.ofSeconds(10))
    .build();

HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("https://httpbin.org/get"))
        .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
        .build();

HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

// print response headers
HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

```

```java
    HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("https://httpbin.org/get"))
        .setHeader("User-Agent", "Java 11 HttpClient Bot")
        .build();

    CompletableFuture<HttpResponse<String>> response =
            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    
    String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);

        System.out.println(result);
```

```java
    HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    Map<Object, Object> data = new HashMap<>();
        data.put("username", "abc");
        data.put("password", "123");
        data.put("custom", "secret");
        data.put("ts", System.currentTimeMillis());

    HttpRequest request = HttpRequest.newBuilder()
        .POST(ofFormData(data))
        .uri(URI.create("https://httpbin.org/post"))
        .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
        .header("Content-Type", "application/x-www-form-urlencoded")
        .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());

        System.out.println(response.body());

```


HttpRequest.BodyPublishers.ofString("String body")

HttpRequest.BodyPublishers
    .ofInputStream(() -> new FileInputStream("test.txt"))

HttpRequest.BodyPublishers.ofByteArray({1,2,3})

HttpRequest.BodyPublishers.fromFile(
    Paths.get("src/test/resources/sample.txt"))

## Response
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());

BodyHandlers.ofByteArray
BodyHandlers.ofString
BodyHandlers.ofFile
BodyHandlers.discarding
BodyHandlers.replacing
BodyHandlers.ofLines
BodyHandlers.fromLineSubscriber


## Custom BodyHandler

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.ResponseInfo;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow;

public class JsonBodyHandler<T> implements BodyHandler<T> {
    private final Class<T> targetClass;
    private final ObjectMapper objectMapper;

    public JsonBodyHandler(Class<T> targetClass, ObjectMapper objectMapper) {
        this.targetClass = targetClass;
        this.objectMapper = objectMapper;
    }

    public JsonBodyHandler(Class<T> targetClass) {
        this(targetClass, new ObjectMapper());
    }

    @Override
    public BodySubscriber<T> apply(ResponseInfo responseInfo) {
        // По желанию можно проверить статус ответа или Content-Type
        int status = responseInfo.statusCode();
        if (status < 200 || status >= 300) {
            // Можно вернуть подписчик, который выбросит исключение или вернёт null
            // Здесь для простоты — просто копим тело, но потом выбросим ошибку при парсинге
        }
        return new JsonBodySubscriber<>(targetClass, objectMapper);
    }

    // Внутренний подписчик, собирающий байты и парсящий JSON
    private static class JsonBodySubscriber<T> implements BodySubscriber<T> {
        private final Class<T> targetClass;
        private final ObjectMapper objectMapper;
        private final List<ByteBuffer> receivedBuffers = new ArrayList<>();
        private final CompletableFuture<T> bodyFuture = new CompletableFuture<>();

        public JsonBodySubscriber(Class<T> targetClass, ObjectMapper objectMapper) {
            this.targetClass = targetClass;
            this.objectMapper = objectMapper;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            subscription.request(Long.MAX_VALUE);
        }

        @Override
        public void onNext(List<ByteBuffer> buffers) {
            receivedBuffers.addAll(buffers);
        }

        @Override
        public void onError(Throwable throwable) {
            bodyFuture.completeExceptionally(throwable);
        }

        @Override
        public void onComplete() {
            try {
                // Склеиваем все буферы в один массив байт
                int totalSize = receivedBuffers.stream()
                        .mapToInt(ByteBuffer::remaining)
                        .sum();
                byte[] bytes = new byte[totalSize];
                int offset = 0;
                for (ByteBuffer buffer : receivedBuffers) {
                    int length = buffer.remaining();
                    buffer.get(bytes, offset, length);
                    offset += length;
                }
                // Десериализация JSON
                T result = objectMapper.readValue(bytes, targetClass);
                bodyFuture.complete(result);
            } catch (Exception e) {
                bodyFuture.completeExceptionally(e);
            }
        }

        @Override
        public CompletionStage<T> getBody() {
            return bodyFuture;
        }
    }
}
```