# Пул соединений

## Вариант с использованием `BlockingQueue`
### `BlockingQueue`.
1. Реализации BlockingQueue являются потокобезопасными. Все методы организации очередей реализуют свои эффекты атомарно, 
используя внутренние блокировки или другие формы управления параллелизмом
```java
interface BlockingQueue<E> extends Queue<E> {
    /*
        Добавляет элемент в очередь, ожидает, если очередь переполнена,
        пока освободится место    
     */
    void put(E e) throws InterruptedException;

    /*
        Извлекает и удаляет начало этой очереди, ожидая, при необходимости, 
        пока элемент не станет доступным    
     */
    E take() throws InterruptedException;
    
    /*
        Извлекает и удаляет начало этой очереди, ожидая указанное время, если очередь пустая, 
        не дождавшись возвращает null     
     */
    E poll(long timeout, TimeUnit unit) throws InterruptedException;
    //...
}
```
2. Проект реализации

```java
import java.sql.Connection;

public class DbConnection {

    public static int CAPACITY = 10;

    private static BlockingQueue<Connection> queue;

    public synchronized void init() {
        if (queue == null) {
            // create queue (ArrayBlockingQueue), add connections
        }
    }

    public static Connection getConnection() {
        // достаем из очереди (возможно с таймаутом)
    }
    
    public static void releas(Connection connection) {
        // возвращаем в очередь
    }

    public static void destroy() {
        // очищаем очередь, закрывая подключения
    }
}
```

## Вариант `Hikari Connections Pool`
1. Зависимость
```java
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>6.2.1</version>
</dependency>
```
2. Работа с использованием `DataSource`
```java
HikariDataSource dataSource;
```
3. Настройка и создание экземпляра
```java
Class.forName("org.postgresql.Driver");

HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:postgresql://localhost:5432/demo");
config.setUsername("postgres");
config.setPassword("postgres");
config.setConnectionTimeout(50000); // в миллисекундах
config.setMaximumPoolSize(10);
dataSource = new HikariDataSource(config);
```
4. Получение экземпляра `Connection`
```java
// Реализация HikariCP использует synchronized
Connection connection = dataSource.getConnection();
```
5. Возвращение в пул экземпляра `Connection`
```java
connection.close();
```
6. Уничтожение пула
```java
dataSource.close();
```