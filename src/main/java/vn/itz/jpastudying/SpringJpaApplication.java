package vn.itz.jpastudying;

import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import vn.itz.jpastudying.config.AppConfig;
import vn.itz.jpastudying.model.HelloWorld;
import vn.itz.jpastudying.model.Person;
import vn.itz.jpastudying.service.PersonService;

@SpringBootApplication
@EnableScheduling
public class SpringJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJpaApplication.class, args);

//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
//				AppConfig.class);

		// Tim hieu ve bean
//		HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorldBean");
//		System.out.println(helloWorld.getMessage());
//		applicationContext.close();

		// Nhap vao ten, tuoi cua mot nguoi. Xuat ra ten in hoa vua nhap
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Nhap ten: ");
//		String name = sc.nextLine();
//		System.out.print("Nhap tuoi: ");
//		Integer age = sc.nextInt();
//		sc.close();
//
//		PersonService personService = context.getBean(PersonService.class);
//		Person person = personService.showNameAndAgePerson(name, age);
//
//		System.out.println("Ten (Viet hoa): " + person.getName());
//		System.out.println("Tuoi: " + person.getAge());
	}

}
