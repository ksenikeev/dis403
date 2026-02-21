# Spring context

1. Подключение библиотек
```xml
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>6.0.6</version>
    </dependency>
```
2. Описание конфигурации контекста
```java
// Config.java

@Configuration
@ComponentScan("ru.itis.dis.component") 
public class Config {

    @Bean
    public BeanType createBeanMethod() {
        //
    }

    @Bean
    @Scope("prototype") // singleton, request, session, application, websocket 
    public BeanType createBeanMethod() {
        //
    }
}
```

3. Описание компонент

4. Создание контекста
```java
       ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
```

5. Получение списка имен бинов
```java
    context.getBeanDefinitionNames();
```

6. Запрос бина
```java
    BeanType bean = (BeanType) context.getBean("beanType");
```

7. Список имен бинов по аннотации
```java
    context.getBeanNamesForAnnotation(Component.class);
```