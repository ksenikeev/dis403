 # Конфигурирование yaml
## YAML – Ain’t Markup Language
- https://yaml.org/

- Расширенная версия JSON (.yaml .yml)

- java, json : {... {} ... }

- python, yaml: 4 space, tab

### Структура - пары ключ-значение

- строки можно использовать без кавычек или с одинарными или с двойными кавычками

```java
<dependency>
    <groupId>org.yaml</groupId>
    <artifactId>snakeyaml</artifactId>
    <version>2.2</version>            
</dependency>
```

```java
    import org.yaml.snakeyaml.LoaderOptions;
    import org.yaml.snakeyaml.Yaml;
    import org.yaml.snakeyaml.constructor.Constructor;

    InputStream inputStream = new FileInputStream("data.yaml"); 
    Yaml yaml = new Yaml();
    Map<String,Object> data = yaml.load(inputStream);

    Yaml yaml = new Yaml(new Constructor(Person.class, new LoaderOptions()));
    InputStream inputStream = new FileInputStream("data.yaml");
    Person person = yaml.load(inputStream);
```