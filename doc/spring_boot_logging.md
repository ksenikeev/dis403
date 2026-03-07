# Логирование в spring boot

- log4j
- JUL — java.util.logging
- JCL — jakarta commons logging (обертка для логгеров)
- Logback (используется по умолчанию, конфигурационный файл logback-spring.xml)
- SLF4J — simple logging facade for java (обертка для оберток логгеров, конфигурационный файл log4j2-spring.xml)



<https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging>
<https://javarush.com/quests/lectures/questspringboot.level01.lecture08>
    
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LogController {
    Logger logger = LoggerFactory.getLogger(LogController.class);
    
    @GetMapping("/log")
    public String doLog() {
        logging.info("log info");
        return "log";
    }
}

```
## Уровни логирования

- ERROR
- WARN
- INFO
- DEBUG
- TRACE

По умолчанию используется уровень `INFO`, вывод в `System.out`.

logging.level.root=warn
logging.level.org.springframework.web=debug
logging.level.org.hibernate=error

## Вывод в файл

logging.file.name=my.log

или

logging.file.path=/home/user/log
logging.pattern.file= %d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%


Свойство                            | Значение                                                   | по умолчанию

logging.file.max-size               | максимальный размер файла                                  | 10 Mb

logging.file.max-history            | сколько дней должны храниться файлы                         | 7

logging.file.total-size-cap         | общий размер архивов журналов                               | нет

logging.file.clean-history-on-start |принудительная очистка архива журнала при запуске приложения | false

## Группировка пакетов в один блок управления

logging.group.tomcat=org.apache.catalina,org.apache.coyote,org.apache.tomcat
logging.level.tomcat=trace

### Предопределенные группы в SpringBoot
- web

org.springframework.core.codec, org.springframework.http, org.springframework.web, org.springframework.boot.actuate.endpoint.web, org.springframework.boot.web.servlet.ServletContextInitializerBeans

- sql

org.springframework.jdbc.core, org.hibernate.SQL, org.jooq.tools.LoggerListener