# Spring Data JPA

https://docs.spring.io/spring-data/jpa/reference/jpa.html

## Конфигурация:

```java
@Configuration
@EnableJpaRepositories("ru.itis.dis301.lab2_06.repository")
@EnableTransactionManagement
@ComponentScan("ru.itis.dis301.lab2_06")
public class Config {

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("ru.itis.dis301.lab2_06.model");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(additionalProperties());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/lab05");
        config.setUsername("postgres");
        config.setPassword("post");
        config.setDriverClassName("org.postgresql.Driver");
        return config;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); //update, none, create
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }
}
```

## Репозиторий, работающий через EntityManager
```java
@Repository
public class ActorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Actor> findAll() {
        Query query = entityManager.createQuery("select a from Actor a");
        return query.getResultList();
    }

    public Actor save(Actor actor) {
        if (actor.getId() != null) {
            return entityManager.merge(actor);
        } else {
            entityManager.persist(actor);
            return actor;
        }
    }
}
```

### CRUD репозиторий

```java
public interface GenreRepository extends CrudRepository<Genre, Long> {

    @Query("select g from Genre g where g.id = :id ")
    Genre getGenre(@Param("id") Long id);
}
```


    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.2.5</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-jpa</artifactId>
            <version>3.4.4</version>
        </dependency>

        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.5</version>
        </dependency>

<!--
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core-jakarta</artifactId>
            <version>5.6.15.Final</version>
        </dependency>
-->

        <dependency>
            <groupId>org.hibernate.orm</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>6.6.13.Final</version>
        </dependency>

        <dependency>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP</artifactId>
            <version>5.0.1</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
        </dependency>
