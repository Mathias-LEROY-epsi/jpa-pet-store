package fr.epsi.b3;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.epsi.b3.entity.Address;
import fr.epsi.b3.entity.Animal;
import fr.epsi.b3.entity.Cat;
import fr.epsi.b3.entity.Fish;
import fr.epsi.b3.entity.PetStore;
import fr.epsi.b3.entity.Product;

public class Main {
    private static final String SELECT_ALL_PETS_FROM_A_PETSTORE = "SELECT a FROM Animal a WHERE a.petStore = :petstore";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstore");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            // create an address
            Address address = new Address();
            address.setNumber("1");
            address.setStreet("rue de la paix");
            address.setCity("Paris");
            address.setZipCode("75000");

            // persist the address
            em.persist(address);

            // create a pet store
            PetStore petStore = new PetStore();
            petStore.setName("Petit animal");
            petStore.setAddress(address);
            petStore.setManagerName("Jean");

            // persist the pet store
            em.persist(petStore);

            // create some pets
            Cat cat = new Cat();
            Calendar cal1 = Calendar.getInstance();
            cal1.set(2022, Calendar.JANUARY, 10);
            cat.setBirth(cal1.getTime());
            cat.setColor("black");
            cat.setChipId("123456789");
            cat.setPetStore(petStore);

            Fish fish = new Fish();
            Calendar cal2 = Calendar.getInstance();
            cal2.set(2022, Calendar.APRIL, 6);
            fish.setBirth(cal2.getTime());
            fish.setColor("blue");
            fish.setLivingEnv(Fish.LivingEnv.SEA_WATER);
            fish.setPetStore(petStore);

            // persist the pets
            em.persist(cat);
            em.persist(fish);

            // create some products
            Product food = new Product();
            food.setLabel("Croquettes");
            food.setPrice(10.0);
            food.setCode("CROQ123");
            food.setType(Product.Type.FOOD);

            Product accessory = new Product();
            accessory.setLabel("Cage");
            accessory.setPrice(100.0);
            accessory.setCode("CAGE123");
            accessory.setType(Product.Type.ACCESSORY);

            Product cleaning = new Product();
            cleaning.setLabel("Nettoyant");
            cleaning.setPrice(5.0);
            cleaning.setCode("NETT123");
            cleaning.setType(Product.Type.CLEANING);

            // persist the products
            em.persist(food);
            em.persist(accessory);
            em.persist(cleaning);

            em.getTransaction().commit();
            System.out.println("Les données ont bien été sauvegardées !");

            //find all pets
            System.out.println("Liste des animaux appartenant à un petStore défini :");
            TypedQuery<Animal> selectAllPets = em.createQuery(SELECT_ALL_PETS_FROM_A_PETSTORE, Animal.class);
            selectAllPets.setParameter("petstore", petStore);
            for (Animal animal : selectAllPets.getResultList()) {
                System.out.println(animal.toString());
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Une erreur est survenue lors de la sauvegarde des données ! Voici le message d'erreur : " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}