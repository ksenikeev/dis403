import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.itis.dis403.lab2_3.model.Admin;
import ru.itis.dis403.lab2_3.model.Client;
import ru.itis.dis403.lab2_3.model.Phone;

public class MainCrEATE {
    public static void main(String[] args) {
        EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("lab2_3");


        Admin admin = new Admin();
        admin.setId(10l);
        admin.setName("admin1");
        admin.setEmail("asd@awd");

        Client client = new Client();
        client.setId(11l);
        client.setName("client2");
        client.setAddress("addr");

        Phone phone1 = new Phone();
        phone1.setId(1l);
        phone1.setNumber("1111111");

        Phone phone2 = new Phone();
        phone2.setId(2l);
        phone2.setNumber("222222");

        client.getPhones().add(phone1);
        client.getPhones().add(phone2);

        admin.getPhones().add(phone2);

        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //entityManager.persist(phone1);
        //entityManager.persist(phone2);

        entityManager.persist(admin);
        entityManager.persist(client);

        transaction.commit();
        entityManager.close();

        emf.close();
    }
}
