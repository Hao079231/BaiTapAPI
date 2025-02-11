package vn.itz.jpastudying.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {
  @Bean
  public DataSource getDataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl("jdbc:mysql://localhost:3306/springbootjpa");
    dataSource.setUsername("root");
    dataSource.setPassword("1234567");
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    return dataSource;
  }
}
