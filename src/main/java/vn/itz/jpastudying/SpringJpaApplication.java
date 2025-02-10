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
	}

}
