import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.dis403.lab2_3.config.Config;
import ru.itis.dis403.lab2_3.model.Phone;
import ru.itis.dis403.lab2_3.service.PhoneService;

import java.util.List;

public class MainSpringJpa {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        Phone phone = new Phone();
        phone.setNumber("22222");
        //phone.setId(2l);

        //PhoneRepository repository = context.getBean(PhoneRepository.class);
        //repository.save(phone);
        PhoneService service = context.getBean(PhoneService.class);
        service.save(phone);

        //List<Phone> phoneList = service.findAll();
        List<Phone> phoneList = service.getPhoneLike("3%");

        phoneList.forEach(p -> System.out.println(p.getNumber()));
    }
}
