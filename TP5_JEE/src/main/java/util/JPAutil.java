package util;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class JPAutil {//le role de cette classe ca va pemetre de faire des connexion avec jpa 
private static EntityManagerFactory factory; //elle va crer se que on appel entity manager et ca va nous retourner   que lentity manager va persister enregister les donner pour l'objet produit 
private static EntityManager entityManager;
public static EntityManager getEntityManager(String persistUnit) 
 {
if (entityManager==null)
{
factory = 
Persistence.createEntityManagerFactory(persistUnit);
 entityManager = factory.createEntityManager();
}
return entityManager;
}
}
