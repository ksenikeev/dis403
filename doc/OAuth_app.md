# OAuth (Open Authorization)


```
flowchart TD
    User(Пользователь)
    Client(OAuth2 Client :8080)
    Auth(Authorization Server :9000)
    Resource(Resource Server :8090)
    User -->|1. Запрос защищенного ресурса| Client
    Client -->|2. Редирект на страницу входа| Auth
    Auth -->|3. Запрос логина/пароля| User
    User -->|4. Ввод учетных данных| Client
    Client -->|5. Обмен кода на токен| Auth
    Auth -->|6. Выдача JWT-токена| Client
    Client -->|7. API-запрос с токеном| Resource
    Resource -->|8. Валидация JWT у сервера авторизации| Auth
    Resource -->|9. Ответ с данными| Client
    Client -->|10. Отображение данных| User
```
#### Зависимости
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-authorization-server</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

#### Регистрация клиента
```yaml
spring:
  security:
    oauth2:
      authorizationserver:
        client:
          my-client:
            registration:
              client-id: "client-id"
              client-secret: "{noop}client-secret"
              client-authentication-methods: ["client_secret_basic"]
              authorization-grant-types: ["authorization_code", "refresh_token"]
              redirect-uris: ["http://127.0.0.1:8080/login/oauth2/code/my-client"]
              scopes: ["openid", "profile"]
            require-authorization-consent: true
```

#### SecurityFilterChain
```java
@Bean
@Order(1)
public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
    http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
        .with(authorizationServerConfigurer, (authorizationServer) ->
            authorizationServer.oidc(Customizer.withDefaults()))
        .authorizeHttpRequests((authorize) ->
            authorize.anyRequest().authenticated())
        .formLogin(Customizer.withDefaults());
    return http.build();
}

@Bean
public RegisteredClientRepository registeredClientRepository() {
    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("client-id")
        .clientSecret("{noop}client-secret")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .redirectUri("http://127.0.0.1:8080/login/oauth2/code/my-client")
        .scope("openid")
        .scope("profile")
        .build();
    return new InMemoryRegisteredClientRepository(registeredClient);
}
```

### Resource Server
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://localhost:9000
```

```java
@RestController
public class MessageController {
    @GetMapping("/api/messages")
    public List<String> messages() {
        return List.of("Message 1", "Message 2", "Message 3");
    }
}
```

### Клиент
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          my-client:
            provider: my-auth-server
            client-id: client-id
            client-secret: client-secret
            authorization-grant-type: authorization_code
            redirect-uri: "http://127.0.0.1:8080/login/oauth2/code/my-client"
            scope: openid, profile
        provider:
          my-auth-server:
            issuer-uri: http://localhost:9000
```

```java
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            model.addAttribute("name", principal.getAttribute("name"));
        }
        return "home";
    }
}
```

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()
        )
        .oauth2Login(Customizer.withDefaults());
    return http.build();
}
```

Запустите Authorization Server (AuthServerApplication на порту 9000).

Запустите Resource Server (ResourceServerApplication на порту 8090).

Запустите Client (ClientApplication на порту 8080).

Перейдите в браузере по адресу http://localhost:8080.

Вы будете перенаправлены на страницу входа сервера авторизации (http://localhost:9000/login).

Войдите, используя учетные данные пользователя из вашего сервера авторизации (поскольку H2 или InMemory детали должны быть настроены в коде).

После успешного входа вас перенаправят обратно в клиентское приложение.

