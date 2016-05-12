package pl.java.scalatech.entity;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomerDTO {
  private String name;

   public CustomerDTO(String name) {
    // TODO Auto-generated constructor stub
}

  @Autowired
  EntityManager entityManager;



}



