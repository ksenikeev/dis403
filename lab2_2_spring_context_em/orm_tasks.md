# ORM

## Объектно-реляционное отображение

### Разработка инструментов автоматического отображения структуры БД на структуру объектной модели

### Примем:

```java
@Entity
class Country {
    @Id
    Long id;
    String name;
}

@Entity
class City {
    @Id
    Long id;
    String name;
    @ManyToOne
    Country country;
}

create table country (
        id serial primary key,
        name varchar(255)
);

create table city (
        id serial primary key,
        name varchar(255),
        country_id bigint
);
```

1. Имя сущностного класса и таблицы на которую он отображается должны совпадать.
2. Члены классов, имеющие объектные типы (сущности), отображаем на поля с суфиксом '_id', 
формирующим внешний ключ, такие члены аннотируем как `@ManyToOne`
3. Управляющая структура описывается интерфейсом EntityManager.
4. Первичный ключ описываем в классе с аннотацией @Id

```java
public interface EntityManager {
    <T> T save(T entity); // insert, update
    void remove(Object entity); // delete ... where id =  
    <T> T find(Class<T> entityType, Object key); // select ... where id =
    <T> List<T> findAll(Class<T> entityType); // select ...
}
```

EntityManageerFactory - создание экземпляра EntityManager, создание пула подключений,
предоставление экземпляру EntityManager источника данных DataSource


## Задача
1. Описываем объектную модель, 3 таблицы, хотя бы 2 из них связаны отношением многие к одному
2. После запуска приложения необходимо просканировать структуру классов и найти сущности
3. Создать структуру БД в реляционной СУБД (сгенерировать по структуре классов SQL команды CREATE TABLE ...)
4. Проверить соответствие Объектной модели и реляционной 
   1) Для каждого класса определить наличие таблицы
   2) Для каждого члена класса определить наличие поля в таблице (типы не контролируем) 
5. Реализовать интерфейс EntityManager
6. Протестировать

