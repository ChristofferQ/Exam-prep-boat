package facades;

import dtos.BoatDTO;
import dtos.OwnerDTO;
import dtos.RenameMeDTO;
import entities.Boat;
import entities.Harbour;
import entities.Owner;
import entities.RenameMe;

import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import utils.EMF_Creator;

public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeExample() {}

    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<OwnerDTO> getAllOwners(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o", Owner.class);
        List<Owner> os = query.getResultList();
        return OwnerDTO.getDtos(os);
    }

    public List<BoatDTO> getBoatsByHarbour(long id){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Boat> query = em.createQuery("SELECT b FROM Boat b WHERE b.harbour.id =:id", Boat.class);
        query.setParameter("id", id);
        List<Boat> bs = query.getResultList();
        System.out.println("Testing getBoatsByHarbour \n" + bs);
        return BoatDTO.getDtos(bs);
    }

    public List<OwnerDTO> getOwnerByBoat(long id){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query = em.createQuery("SELECT b.owner FROM Boat b WHERE b.id =:id", Owner.class);
        query.setParameter("id",id);
        List<Owner> os = query.getResultList();
        System.out.println("Testing getOwnerByBoat \n" + os);
        return OwnerDTO.getDtos(os);
    }

    public BoatDTO createBoat(BoatDTO b){
        Boat be = new Boat(b.getBrand(), b.getMake(), b.getName(), b.getImage());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(be);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BoatDTO(be);
    }

    public BoatDTO connectBoatWithHarbour(BoatDTO boatDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            Boat b = em.find(Boat.class, boatDTO.getId());

            b.setHarbour(boatDTO.getHarbour());

            em.getTransaction().begin();
            em.merge(b);
            em.getTransaction().commit();
            return new BoatDTO(b);
        } finally {
            em.close();
        }
    }

    public BoatDTO editBoat(BoatDTO boatDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            Boat b = em.find(Boat.class, boatDTO.getId());

            b.setBrand(boatDTO.getBrand());
            b.setMake(boatDTO.getMake());
            b.setName(boatDTO.getName());
            b.setImage(boatDTO.getImage());

            em.getTransaction().begin();
            em.merge(b);
            em.getTransaction().commit();
            return new BoatDTO(b);
        } finally {
            em.close();
        }
    }

    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
        fe.getBoatsByHarbour(2);
        fe.getOwnerByBoat(2);

    }
}
