package vn.itz.jpastudying.service;

import org.springframework.stereotype.Service;
import vn.itz.jpastudying.model.Person;

@Service
public class PersonService {

  public Person showNameAndAgePerson(String name, Integer age) {
    return new Person(name.toUpperCase(), age);
  }
}
