# Servlets

## Контекст приложения
1. Путь к нашему приложению
```
Prot    IP:port/context_name/resource 

http://127.0.0.1:8080/lab07/simple

lab07 - контекст веб-приложения
```

## Структура веб-приложения

application.war
- WEB-INF
    - classes
    - lib
    - resources 
    - web.xml

## Структура каталогов проекта
- java
- resources
    - webapps
    - web.xml

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SimpleSpringSecurity</title>
</head>
<body>

    <h1>SimpleSpringSecurity</h1>

</body>
</html>
```


https://plugins.jetbrains.com/plugin/9492-smart-tomcat

https://github.com/zengkid/SmartTomcat

# web.xml
```xml
<servlet>
    <servlet-name>fooServlet</servlet-name>
    <servlet-class>com.example.FooServlet</servlet-class>
    <init-param>
        <param-name>flip</param-name>
        <param-value>flop</param-value>
    </init-param>
</servlet>

<servlet-mapping>
    <servlet-name>fooServlet</servlet-name>
    <url-pattern>/fooServlet</url-pattern>
    <url-pattern>/fooServlet/*</url-pattern>
</servlet-mapping>
```
```java
@WebServlet(
        name = "MyServletName",
        description = "MyDescription",
        urlPatterns = {"/my-service/api"},
        initParams={
            @WebInitParam(name="myParameter1", value="Not provided"),
            @WebInitParam(name="myParameter2", value="Not provided")
        }
    )
```