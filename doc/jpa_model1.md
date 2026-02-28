# JPA - спецификация, описывающая принципы ORM (object-relation mappings) в Java

## Построение объектной модели базы данных

1. Класс-сущность должен иметь аннотацию `@Entity`
2. Класс отображается на таблицу, поэтому должен иметь описание первичного ключа. 
Над полем-первичным ключем ставится аннотация `@Id`

```
@Entity
public class Passenger {
    @Id
    private Long id;

    private String name;
    private String phone;
}
```

## Автоматическая генерация первичного ключа

1. В "ручном режиме" генерации (назначаем id программно) никаких дополнительных средств определения не требуется.

2. При делегировании генерации значений первичного ключа в СУБД используем аннотацию @GenratedValue. Поведение определяется атрибутом strategy (со значением из enum GenerationType { AUTO, SEQUENCE, IDENTITY, TABLE }) AUTO — значение по умолчанию.

AUTO:
```
@Id
@GeneratedValue
private Long id;
```
При этом в БД описание поля id
```id bigint NOT NULL PRIMARY KEY```
Для генерации значений будет использоваться последовательность с именем
hibernate_sequence
Причем эта последовательность будет использована для всех таблиц и первичных ключей, определенных таким образом.

SEQUENCE:
Дополнительно необходимо определить генератор @SequenceGenerator
```
@Id
@SequenceGenerator(name="tblgen", sequenceName="tbl_seq", allocationSize=1)
@GeneratedValue(strategy=GenerationTypeю.SEQUENCE, generator="tblgen")
private Long id;
```

БД описание поля id
```id bigint NOT NULL PRIMARY KEY```
Для генерации значений будет использоваться последовательность с именем tbl_seq


IDENTITY:
```
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
```
Для такого определения будет сформирована последовательность по правилу имятаблицы_id_seq. Эта последовательность будет использована для задания уникального значения первичного ключа. В БД определение таблицы примет вид

```id bigint NOT NULL PRIMARY KEY DEFAULT nextval('имятаблицы_id_seq');```

## Отображение при наследовании

    • При наследовании информация оказывается распределенной по нескольким объектам. 
    • Как строить таблицы в таком случае? 
    • Как восстанавливать потом иерархию классов из таблиц при запросах?

Спецификация JPA предлагает несколько стратегий для разрешения этих вопросов. Специальным образом размечая классы (мы будем делать это с помощью аннотаций), мы даем указание на определенное поведение системы ORM.

Рассмотрим разные стратегии на примере двух классов Person и Client:

class Person {
String name;
}

class Client extends Person {
String passport;
String address;
}

@MappedSuperClass

Проставляем аннотацию @MappedSuperClass над определение класса предка

@MappedSuperclass
public class Person {

    @Id
    private Long id;

    String name;
	…
}

Класс Person может быть абстрактным.

@Entity
public class Client extends Person{

    private String passport;

    private String address;
	…
}

Таким образом определенная иерархия наследования будет отображаться на таблицу
CREATE TABLE client
(
id bigint NOT NULL,
name character varying(255),
address character varying(255),
passport character varying(255),
CONSTRAINT client_pkey
PRIMARY KEY (id)
);

Мы видим, что все атрибуты объединены в одну таблицу client.

@Inheritance(strategy = JOINED)

Проставляем аннотацию @Inheritance(strategy = JOINED) над определение класса предка

@Entity
@Inheritance(strategy = JOINED)
public class Person {

    @Id
    private Long id;

    String name;
	…
}

Класс Person может быть абстрактным.

@Entity
public class Client extends Person{

    private String passport;

    private String address;
	…
}

Классы отображаются на таблицы

CREATE TABLE person
(
id bigint NOT NULL,
name character varying(255),
CONSTRAINT person_pkey
PRIMARY KEY (id)
);

CREATE TABLE client
(
id bigint NOT NULL,
address character varying(255),
passport character varying(255),
CONSTRAINT client_pkey
PRIMARY KEY (id),
CONSTRAINT person_fk
FOREIGN KEY (id)
REFERENCES person (id) ...
);

Сохраняя класс Client, мы получаем одновременно записи в две таблицы с одинаковым значением первичного ключа:


@Inheritance(strategy = SINGLE_TABLE)

Эта стратегия предполагает отображение на одну таблицу с именем корневого класса предка

@Entity
@Inheritance(strategy =SINGLE_TABLE)
public class Person {

    @Id
    private Long id;

    String name;
	…
}

@Entity
public class Client extends Person{

    private String passport;

    private String address;
	…
}

Отображаемая таблица строится по имени корневого класса предка (Person в нашем случае)

CREATE TABLE person
(
dtype character varying(31)NOT NULL,
id bigint NOT NULL,
name character varying(255),
address character varying(255),
passport character varying(255),
CONSTRAINT person_pkey PRIMARY KEY (id)
);

Появляется дополнительное поле dtype character varying(31) — хранит информацию об имени класса потомке (ПОМНИТЕ ПРО ОГРАНИЧЕНИЕ В 31 СИМВОЛ, ИМЕНУЯ КЛАССЫ!)

@Inheritance(strategy = TABLE_PER_CLASS)

Отдельная независимая таблица на класс, при этом класс-потомок отображается на таблицу, включающую поля с нижележащих иерархий.
В этом случае для  каждого класса необходимо наличие поля с аннотацией   @Id

@Entity
@Inheritance(strategy=TABLE_PER_CLASS)
public class Person {

    @Id
    private Long id;

    String name;
	…
}

@Entity
public class Client extends Person{

    @Id
    private Long id;
 
    private String passport;

    private String address;
	…
}

CREATE TABLE person
(
id bigint NOT NULL,
name character varying(255),
CONSTRAINT person_pkey PRIMARY KEY (id)
);

CREATE TABLE public.client
(
id bigint NOT NULL,
name character varying(255),
address character varying(255),
passport character varying(255),
CONSTRAINT client_pkey PRIMARY KEY (id)
);

Выбор стратегии определяется логикой работы приложения и инструментарием, который обеспечивает эту логику.


        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.28</version>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.6.0</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>6.6.13.Final</version>
        </dependency>

@Inheritance(strategy =SINGLE_TABLE)
@Inheritance(strategy = TABLE_PER_CLASS)
