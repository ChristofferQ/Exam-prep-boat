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
import javax.persistence.*;
import javax.ws.rs.core.Response;

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

    public BoatDTO getBoatById(long boatId){
        EntityManager em = emf.createEntityManager();
        Boat b = em.find(Boat.class, boatId);
        return new BoatDTO(b);
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


    public BoatDTO connectBoatWithHarbour(long boatId, long harbourId) {
        EntityManager em = emf.createEntityManager();
        try {
            Boat b = em.find(Boat.class, boatId);
            Harbour harbour = em.find(Harbour.class, harbourId);

            b.setHarbour(harbour);
            harbour.addBoat(b);

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
            Harbour h = em.find(Harbour.class, boatDTO.getHarbourId());
            Owner o = em.find(Owner.class, boatDTO.getOwnerId());

            b.setBrand(boatDTO.getBrand());
            b.setMake(boatDTO.getMake());
            b.setName(boatDTO.getName());
            b.setImage(boatDTO.getImage());
            b.setHarbour(h);
            b.setOwner(o);

            em.getTransaction().begin();
            em.merge(b);
            em.getTransaction().commit();
            return new BoatDTO(b);
        } finally {
            em.close();
        }
    }

    public Response deleteBoat(long id){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
         //   Query q = em.createQuery("DELETE FROM Boat b WHERE b.id = :id").setParameter("id",id);
        Query q = em.createQuery("DELETE FROM Boat b WHERE b.id = :id").setParameter("id",id);
            int deleteBoat = q.executeUpdate(); //The hell is this?

            em.getTransaction().commit();
            return Response.ok(deleteBoat).build();
        } finally {
            em.close();
        }
    }

    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
        fe.getBoatsByHarbour(2);
        fe.getOwnerByBoat(2);
        fe.connectBoatWithHarbour(4,2);
        BoatDTO be = fe.getBoatById(1);
        be.setOwnerId(2);
        be.setHarbourId(2);
        fe.editBoat(be);
        System.out.println("Testing editBoat" + "\n" + be);


    }
}
