package utils;


import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "test123");
    User admin = new User("admin", "test123");
    User both = new User("user_admin", "test123");

    Owner owner1 = new Owner("name1", "address1", 1234);
    Owner owner2 = new Owner("name2", "address2", 5678);

    Harbour harbour1 = new Harbour("name1", "address1",5550);
    Harbour harbour2 = new Harbour("name2", "address2",105000);

    Boat boat1 = new Boat("brand1", "make1", "name1", "image1",owner1,harbour1);
    Boat boat2 = new Boat("brand2", "make2", "name2", "image2",owner2,harbour2);
    Boat boat3 = new Boat("brand3","make3","name3","image3", owner1, harbour2);

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.persist(owner1);
    em.persist(owner2);
    em.persist(harbour1);
    em.persist(harbour2);
    em.persist(boat1);
    em.persist(boat2);
    em.persist(boat3);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
