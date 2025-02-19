package vn.itz.jpastudying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
@EnableFeignClients
public class SpringJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJpaApplication.class, args);
	}

}
