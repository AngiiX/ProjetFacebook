package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Bdd.bdd;

public class Post 
{
	int id;
	int iduser;
	String texte;
	
	private static String FETCH_POST_SQL = "SELECT id, idUser, texte FROM post ORDER BY id DESC";
	private static String ADD_POST_SQL = "INSERT INTO post(id,idUser,texte) VALUES (NULL,'";
	private static String COMM_POST_SQL = "SELECT commentaire.texte, commentaire.id, commentaire.idPost, commentaire.idUser FROM commentaire WHERE ";
	private static String PROPRIETAIRE_POST_SQL = "SELECT utilisateur.* FROM utilisateur INNER JOIN post on utilisateur.id = post.idUser WHERE ";
	private static String TEXTE_POST_SQL = "SELECT id, idUser, texte FROM post WHERE ";
	public Post(int id_, int iduser_, String texte_)
	{
		this.id = id_;
		this.iduser = iduser_;
		this.texte = texte_;
	}
	public Post(int iduser_, String contenu_)
	{
		this.iduser = iduser_;
		this.texte = contenu_;
	}
	
	public int getId() {
		return id;
	}
	public int getIduser() {
		return iduser;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	
	public ArrayList<Post> recupPost()
	{
		ArrayList<Post> listPost = new ArrayList<Post>();
		Connection connection = bdd.getInstance();
		Statement stmt;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(FETCH_POST_SQL);
			// Loop over the database result set and create the
			// user objects.
			while (rs.next()) {
				
				Post p = new Post(rs.getInt("id"), rs.getInt("idUser"), rs.getString("texte"));
				listPost.add(p);
				
			}
			// Free resources
			rs.close();
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		return listPost;
	}
	
	public boolean addPost()
	{
		boolean ajout = false;
		Connection connection = bdd.getInstance();
		Statement stmt;
		
		try {
			stmt = connection.createStatement();
		
			// Loop over the database result set and create the
			// user objects.
			 stmt.executeUpdate(ADD_POST_SQL 	+ this.iduser + "','"
												+ this.texte + "');");
			 stmt.close();
			 ajout = true;
			 return ajout;

		}catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		} 
	}
	public ArrayList<Commentaire> possedeCommentaire()
	{

		ArrayList<Commentaire> commentaire = new ArrayList<Commentaire>();
		Connection connection = bdd.getInstance();
		Statement stmt;
		int i=0;
		try {
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(COMM_POST_SQL+" idPost = "+this.id+";");
			// Loop over the database result set and create the
			// user objects.
			
			while (rs.next()) 
			{	
				i++;
				Commentaire c1 = new Commentaire(rs.getInt("id"),rs.getInt("idUser"),rs.getInt("idPost"),rs.getString("texte"));
				if(c1.getIdPost()==this.id)
				{
					commentaire.add(c1);
				}			
			}
			 rs.close();
			 stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commentaire;
	}
	public String recupProprietaire()
	{
		Connection connection = bdd.getInstance();
		Statement stmt;
		String pseudo="";
		try {
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(PROPRIETAIRE_POST_SQL+" post.id = "+this.id);
			// Loop over the database result set and create the
			// user objects.
			while (rs.next()) {
				Utilisateur u1 = new Utilisateur(rs.getString("nom"),rs.getString("prenom"),rs.getString("pseudo"),rs.getString("email"),rs.getString("mdp"));
				pseudo = u1.getPseudo();
				return pseudo;
			}
			 
			 rs.close();
			 stmt.close();

		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return pseudo;
	}
	public String recupTexte(int idpost)
	{
		Connection connection = bdd.getInstance();
		Statement stmt;
		String texte="";
		try {
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(PROPRIETAIRE_POST_SQL+" id = "+idpost);
			// Loop over the database result set and create the
			// user objects.
			while (rs.next()) {
				Post p1 = new Post(rs.getInt("idUser"),rs.getString("texte"));
				texte = p1.getTexte();
				return texte;
			}
			 
			 rs.close();
			 stmt.close();

		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return texte;
	}
}
