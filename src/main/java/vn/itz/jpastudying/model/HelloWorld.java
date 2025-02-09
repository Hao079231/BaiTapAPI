package vn.itz.jpastudying.model;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HelloWorld {
  private String message;

  @PostConstruct
  public void init(){
    System.out.println("Phuong thuc init(): duoc goi sau khi bean duoc khoi tao");
  }

  @PreDestroy
  public void destroy(){
    System.out.println("Phuong thuc destroy(): duoc goi truoc khi bean bi huy");
  }
}
