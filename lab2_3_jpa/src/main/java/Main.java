import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.itis.dis403.lab2_3.model.Admin;
import ru.itis.dis403.lab2_3.model.Client;
import ru.itis.dis403.lab2_3.model.Person;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("lab2_3");

/*
        Admin admin = new Admin();
        admin.setId(5l);
        admin.setName("admin1");
        admin.setEmail("asd@awd");

        Client client = new Client();
        client.setId(6l);
        client.setName("client2");
        client.setAddress("addr");

        Person person = new Person();
        person.setId(7l);

        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(admin);
        entityManager.persist(client);
        entityManager.persist(person);

        transaction.commit();
        entityManager.close();
*/
        emf.close();
    }
}
