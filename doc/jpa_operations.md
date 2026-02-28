# JPQL Language
https://docs.oracle.com/cd/E11035_01/kodo41/full/html/ejb3_langref.html#ejb3_langref_bulk_ops

# Native SQL
```java
    Query query = em.createNativeQuery("select * from Actor a ", Actor.class);
    List<Actor> result = query.getResultList();
```

# SELECT 
```java
    Query query = em.createQuery("select a from Actor a ");
    List<Actor> result = query.getResultList();
```     
