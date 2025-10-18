# JDBC

## Connection - подключение

## Statement, PreparedStatement - "контекст выполнения запроса"

## ResultSet - "итератор" по набору данных

1. Наличие драйвера БД, реализующего спецификацию JDBC
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.5</version>
</dependency>
```
2. Загрузка класса-драйвера
```java
Class.forName("org.postgresql.Driver");
```
3. Подключение к БД
```java
Connection connection =
                    DriverManager.getConnection(
                            // адрес БД , имя пользователя, пароль
                            "jdbc:postgresql://localhost:5432/demo","postgres","passwd");
/*
        Работа с БД
 */
connection.close();
```
4. Формирование запроса

`Statement` - интерфейс, описывающий выполнение заранее сформированного запроса
```java
Statement statement = connection.createStatement();
// Запрос SELECT
ResultSet resultSet = statement.executeQuery("SELECT * FROM airplanes");
// Запрос UPDATE (INSERT, DELETE)
int updatedRows = statement.executeUpdate("UPDATE airplanes SET code = '1'");
// Универсальный вызов (можно выполнить любой SQL запрос)
statement.execute("ALTER TABLE airplane ADD COLUMN code varchar(50)");

statement.close();
```
5. Формирование запроса динамически
```java
String sql = "select * from bookings.airplanes_data where airplane_code = ? ";
PreparedStatement statement = connection.prepareStatement(sql);

statement.setString(1, "35X");

ResultSet resultSet = statement.executeQuery();

while (resultSet.next()) {
    System.out.print(resultSet.getString("airplane_code") + ";");
    System.out.print(resultSet.getString("model") + ";");
    System.out.print(resultSet.getString("range") + ";");
    System.out.println(resultSet.getString("speed"));
}

resultSet.close();

statement.close();
```
Дополнительный "эффект" использования `PreparedStatement` - защита от SQL инъекций.
6. Получение результата выборки
```java
ResultSet resultSet = statement.executeQuery("select * from bookings.airplanes_data");

while (resultSet.next()) {
    System.out.print(resultSet.getString("airplane_code") + ";");
    System.out.print(resultSet.getString("model") + ";");
    System.out.print(resultSet.getString("range") + ";");
    System.out.println(resultSet.getString("speed"));
}

resultSet.close();
```
# Работа с БД в веб приложении
## ServletContextListener
