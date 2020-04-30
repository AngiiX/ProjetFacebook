package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Bdd.bdd;

public class Utilisateur 
{
	int id;
	String prenom;
	String nom;
	String email;
	String mdp;
	String pseudo;
	
	private static String FETCH_UTILISATEUR_SQL = "SELECT id, nom, prenom, pseudo, email, mdp FROM utilisateur";
	private static String ADD_UTILISATEUR_SQL = "INSERT INTO utilisateur(nom,prenom,pseudo,email,mdp) VALUES ('";
	private static String RELATION_UTILISATEUR_SQL = "SELECT * FROM relation";
	private static String AJOUTRELATION_UTILISATEUR_SQL = "INSERT INTO relation(id,idUser1,idUser2) VALUES ('";
	
	public Utilisateur(int id_, String nom_, String prenom_,String pseudo_, String email_, String mdp_)
	{
		this.id = id_;
		this.nom = nom_;
		this.prenom = prenom_;
		this.pseudo = pseudo_;
		this.email = email_;
		this.mdp = mdp_;
		
	}
	public Utilisateur(String nom_, String prenom_,String pseudo_, String email_ , String mdp_)
	{
		this.prenom = prenom_;
		this.nom = nom_;
		this.pseudo = pseudo_;
		this.email= email_;
		this.mdp = mdp_;
		
	}
	
public int getId() {
	return id;
}
public String getPrenom() {
	return prenom;
}
public String getNom() {
	return nom;
}
public String getEmail() {
	return email;
}
public String getMdp() {
	return mdp;
}

public String getPseudo() {
	return pseudo;
}

public void setPseudo(String pseudo) {
	this.pseudo = pseudo;
}
public void setId(int id) {
	this.id = id;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public void setEmail(String email) {
	this.email = email;
}
public void setMdp(String mdp) {
	this.mdp = mdp;
}

public ArrayList<Utilisateur> recupUsers()
{
	ArrayList<Utilisateur> listUsers = new ArrayList<Utilisateur>();
	Connection connection = bdd.getInstance();
	Statement stmt;

	try {
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(FETCH_UTILISATEUR_SQL);
		// Loop over the database result set and create the
		// user objects.
		while (rs.next()) {
			
			Utilisateur u = new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("pseudo"), rs.getString("email"), rs.getString("mdp"));
			listUsers.add(u);
			
		}
		// Free resources
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
	} 
	return listUsers;
}
public boolean testPseudo()
{
	boolean test = false;
	Connection connection = bdd.getInstance();
	Statement stmt;

	try {
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(FETCH_UTILISATEUR_SQL);
		// Loop over the database result set and create the
		// user objects.
		while (rs.next()) {
			
			Utilisateur u = new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("pseudo"), rs.getString("email"), rs.getString("mdp"));
			if(this.pseudo.equals(u.getPseudo()))
			{
				return true;
			}
		}
		// Free resources
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
	} 
	return test;
	
}
public boolean testEmail()
{
	boolean test = false;
	Connection connection = bdd.getInstance();
	Statement stmt;

	try {
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(FETCH_UTILISATEUR_SQL);
		// Loop over the database result set and create the
		// user objects.
		while (rs.next()) {
			
			Utilisateur u = new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("pseudo"), rs.getString("email"), rs.getString("mdp"));
			if(this.email.equals(u.getEmail()))
			{
				return true;
			}
		}
		// Free resources
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
	} 
	return test;
	
}
public boolean identification()
{
	boolean identification = false;
	ArrayList<Utilisateur> listUsers = new ArrayList<Utilisateur>();
	Connection connection = bdd.getInstance();
	Statement stmt;

	try {
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(FETCH_UTILISATEUR_SQL);
		// Loop over the database result set and create the
		// user objects.
		while (rs.next()) {
			
			Utilisateur u = new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("pseudo"), rs.getString("email"), rs.getString("mdp"));
			//System.out.println(u.email);
			if(this.email.equals(u.email) && this.mdp.equals(u.mdp))
			{
				this.setPrenom(rs.getString("prenom"));
				this.setNom(rs.getString("nom"));
				this.setPseudo(rs.getString("pseudo"));
				this.setId(rs.getInt("id"));
				return true;
			}
			
		}
		// Free resources
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
	} 
	return identification;
}

public boolean addUser()
{
	boolean ajout = false;
	Connection connection = bdd.getInstance();
	Statement stmt;
	
	try {
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(FETCH_UTILISATEUR_SQL);
		// Loop over the database result set and create the
		// user objects.
		while (rs.next()) {
			if(this.email.equals(rs.getString("email")))
			{// email deja pris
				return false;
			}
			
		}
		 stmt.executeUpdate(ADD_UTILISATEUR_SQL + this.nom + "','"
											+ this.prenom + "','" 
											+ this.pseudo + "','"
											+ this.email + "','"
											+ this.mdp + "');");
		 
		 rs.close();
		 stmt.close();
		 ajout = true;
		 return ajout;

	}catch (SQLException e) {
		e.printStackTrace();
		
		return false;
	} 
}

public ArrayList<Relation> possedeRelation()
{
	ArrayList<Relation> listRelation = new ArrayList<Relation>();
	Connection connection = bdd.getInstance();
	Statement stmt;

	try {
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(RELATION_UTILISATEUR_SQL);
		// Loop over the database result set and create the
		// user objects.
		while (rs.next()) {
			
			
				Relation r1 = new Relation(rs.getInt("idUser1"),rs.getInt("idUser2"));
				if(r1.getIdUser1() == this.getId())
                {
                    listRelation.add(r1);
                }
                if(r1.getIdUser2() == this.getId())
                {
                    listRelation.add(r1);
                }
		}
		// Free resources
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
	} 
	return listRelation;
}
//Actu de l'utilisateur
public ArrayList<Post> actuUser()
{
	
	ArrayList<Post>actu = new ArrayList<Post>();
	Post p1 = new Post(0,null);
	ArrayList<Post> post = p1.recupPost();
	//remplir post
	for(int i = 0; i <post.size();i++)
	{
		if(estEnRelation(post.get(i).getIduser()))
		{
			actu.add(post.get(i));
		}
	}
	return actu;
}
//estEnRelation avec id...
public boolean estEnRelation(int idUser2)
{
	ArrayList<Relation> listRelation = possedeRelation();
	for(int i = 0; i<listRelation.size();i++)
	{
		if(this.id==listRelation.get(i).getIdUser1() && idUser2==listRelation.get(i).getIdUser2())
		{
			return true;
		}
		else if(this.id==listRelation.get(i).getIdUser2() && idUser2==listRelation.get(i).getIdUser1())
		{
			return true;
		}
	}
	return false;
}

public boolean addRelation(String pseudo)
{
	boolean ajout = false;
	Connection connection = bdd.getInstance();
	Statement stmt;
	
	try {
		stmt = connection.createStatement();
	
		ResultSet rs = stmt.executeQuery(AJOUTRELATION_UTILISATEUR_SQL);
		// Loop over the database result set and create the
		// user objects.
		while (rs.next()) {
		}		
		 stmt.executeUpdate(AJOUTRELATION_UTILISATEUR_SQL + null + "','"
											+ this.id + "', (SELECT id FROM utilisateur WHERE pseudo LIKE \'" 
											+ pseudo + "\') );");
	//	 NULL, 'idSession', (SELECT id FROM `utilisateur` WHERE pseudo LIKE \"lePseudo\") )";
		 
		 rs.close();
		 stmt.close();
		 ajout = true;
		 return ajout;

	}catch (SQLException e) {
		e.printStackTrace();
		
		return false;
	} 
}
}
