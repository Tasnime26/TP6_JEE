package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity 
public class Categorie implements Serializable {
@Id
@GeneratedValue (strategy=GenerationType.IDENTITY) 
private Long idCat;
private String nomCat;
@Temporal( TemporalType.DATE )                                         //pour les attributs de type date il faut une annotation @Temporal cad je demande a mysql de me creer la date en tantque type date 
private Date dateCreation;                                         //TemporalType cest un inume qui contient plusieurs type ca depend que l'on veut stocker seulment la date 3la haka .date 

@OneToMany (mappedBy="categorie")                                 //one to many 5ater one categorie has many product 
 private List<Produit> produits;  
                                                                     //mappedBy="categorie" ca veut dire qu'on veut ajouter dans l'entite produit un champ qui s'appel categorie il sera de type categorie 
public Categorie() {
super();
}
public Categorie(String nomCat, Date dateCreation) {
super();
this.nomCat = nomCat;
this.dateCreation = dateCreation;
}
public Long getIdCat() {
return idCat;
}
public void setIdCat(Long idCat) {
this.idCat = idCat;
}
public String getNomCat() {
return nomCat;
}
public void setNomCat(String nomCat) {
this.nomCat = nomCat;
}
public Date getDateCreation() {
return dateCreation;
}
public void setDateCreation(Date dateCreation) {
this.dateCreation = dateCreation;
}
public List<Produit> getProduits() {
return produits;
}
public void setProduits(List<Produit> produits) {
this.produits = produits;
}
}
