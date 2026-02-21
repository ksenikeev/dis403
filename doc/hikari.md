<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.4</version>
</dependency>

<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>6.2.1</version>
</dependency>

Class.forName("org.postgresql.Driver");

HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:postgresql://localhost:5432/music");
config.setUsername("postgres");
config.setPassword("postgres");
config.setConnectionTimeout(50000);
config.setMaximumPoolSize(10);
dataSource = new HikariDataSource(config);
