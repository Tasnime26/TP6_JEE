package web;

import java.io.IOException;
import org.apache.catalina.connector.Response;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategorieDaoImpl;
import dao.ICategorieDao;
import dao.IProduitDao;
import dao.ProduitDaoImpl;
import metier.entities.Categorie;
import metier.entities.Produit;
@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
IProduitDao metier;
ICategorieDao metierCat;
@Override
public void init() throws ServletException {
metier = new ProduitDaoImpl();
metierCat = new CategorieDaoImpl();
}
@Override
protected void doGet(HttpServletRequest request,//elle va tester si j'ai dans l'url index.do njm n7ot index.php ay 7aja il va faire un forword pour la vue produit .jsp
 HttpServletResponse response) 
 throws ServletException, IOException {
String path=request.getServletPath();
if (path.equals("/index.do"))
{
request.getRequestDispatcher("produits.jsp").forward(request,response);
}
else if (path.equals("/chercher.do"))//
{
String motCle=request.getParameter("motCle");
ProduitModele model= new ProduitModele();//une instance de la classe ProduitModele
model.setMotCle(motCle);// il a remplie l'objet modele il a affecter le mots cle 
List<Produit> prods = metier.produitsParMC(motCle);//list produit retourner par le methode produitsParMC 
model.setProduits(prods);
request.setAttribute("model", model);
request.getRequestDispatcher("produits.jsp").forward(request,response);
}
else if (path.equals("/saisie.do") )
{
	List<Categorie> cats = metierCat.getAllCategories();
	CategorieModele model= new CategorieModele();
	model.setCategories(cats);
	request.setAttribute("catModel", model);
request.getRequestDispatcher("saisieProduit.jsp").forward(request,response);
}
else if (path.equals("/save.do") && request.getMethod().equals("POST"))
{
	String nom=request.getParameter("nom");
	Long categorieId=Long.parseLong(request.getParameter("categorie"));
	 double prix = Double.parseDouble(request.getParameter("prix"));
	 Categorie cat = metierCat.getCategorie(categorieId); 
	Produit p = metier.save(new Produit(nom,prix,cat));
	request.setAttribute("produit", p);
	response.sendRedirect("chercher.do?motCle=");

//Produit p = metier.save(new Produit(nom,prix));// elle va creer un noveau produit et elle va l'enregistrer save(new Produit(nom,prix))

//request.getRequestDispatcher("confirmation.jsp").forward(request,response);
//response.sendRedirect("chercher.do?motCle=");
}
else if (path.equals("/supprimer.do"))
{
 Long id= Long.parseLong(request.getParameter("id"));
 metier.deleteProduit(id);
 response.sendRedirect("chercher.do?motCle=");//thezk lil home 
}
else if (path.equals("/editer.do") )
{
	Long id= Long.parseLong(request.getParameter("id"));
	 Produit p = metier.getProduit(id);
	 request.setAttribute("produit", p);
	 
	 List<Categorie> cats = metierCat.getAllCategories();
	 CategorieModele model= new CategorieModele();
	 model.setCategories(cats);
	 request.setAttribute("catModel", model);
request.getRequestDispatcher("editerProduit.jsp").forward(request,response);
}
else if (path.equals("/update.do") )
{
	 Long id = Long.parseLong(request.getParameter("id"));
	 String nom=request.getParameter("nom");
	 double prix = Double.parseDouble(request.getParameter("prix"));
	 Long categorieId=Long.parseLong(request.getParameter("categorie"));
	 Produit p = new Produit();
	 p.setIdProduit(id);
	 p.setNomProduit(nom);
	 p.setPrix(prix);
	 Categorie cat = metierCat.getCategorie(categorieId);
	 p.setCategorie(cat);
	 metier.updateProduit(p);
	 response.sendRedirect("chercher.do?motCle=");
//request.setAttribute("produit", p);//une fois le produit et modifier on ajoute le modele produit et on passe a la page confiramtion.jsp 

}

else
{
response.sendError(Response.SC_NOT_FOUND); // en cas quand il ne trouve pas url il va afficher l 'error 
}
}
@Override
protected void doPost(HttpServletRequest request, //do podt quand j'ai un post le dopost va appeler le do get et en saisie produit on a lancer la methode post dans le formulaire puisque la methode et do post le controlleur va executer la methode do post qui a son tour va appeler la methode get la ou on a fait tous les traitements (en haut) 
 HttpServletResponse response) throws 
ServletException, IOException {
doGet(request,response);
}
}

