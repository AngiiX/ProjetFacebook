package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Bdd.bdd;

public class Commentaire 
{
	int id;
	int idPost;
	int idUser;
	String texte;
	
	private static String FETCH_COMMENTAIRE_SQL = "SELECT id, idUser, idPost, texte FROM commentaire ";
	private static String ADD_COMMENTAIRE_SQL = "INSERT INTO `commentaire` (`id`, `idUser`, `idPost`, `texte`) VALUES (NULL,'";
	private static String PROPRIETAIRE_COMMENTAIRE_SQL = "SELECT utilisateur.* FROM utilisateur INNER JOIN commentaire on utilisateur.id = commentaire.idUser WHERE ";
	public Commentaire(int id_, int idUser_,int idPost_, String texte_)
	{
		
		this.id = id_;
		this.idPost = idPost_;
		this.idUser = idUser_;
		this.texte = texte_;
		
	}
	public Commentaire(int idUser_,int idPost_, String texte_)
	{
		this.idUser = idUser_;
		this.idPost = idPost_;
		this.texte = texte_;
	}
	public int getId() {
		return id;
	}
	public int getIdUser() {
		return idUser;
	}
	public int getIdPost() {
		return idPost;
	}
	public String getTexte() {
		return texte;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public ArrayList<Commentaire> recupCommentaire()
	{
		ArrayList<Commentaire> listCommentaire = new ArrayList<Commentaire>();
		Connection connection = bdd.getInstance();
		Statement stmt;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(FETCH_COMMENTAIRE_SQL);
			// Loop over the database result set and create the
			// user objects.
			while (rs.next()) {
				
				Commentaire c= new Commentaire(rs.getInt("id"), rs.getInt("idUser"), rs.getInt("idPost"), rs.getString("texte"));
				listCommentaire.add(c);
				
			}
			// Free resources
			rs.close();
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		return listCommentaire;
	}
	public boolean addCommentaire()
	{
		boolean ajout = false;
		Connection connection = bdd.getInstance();
		Statement stmt;
		
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(ADD_COMMENTAIRE_SQL + this.idUser + "','" 
												+ this.idPost + "','"
												+ this.texte + "');");
			System.out.println(ADD_COMMENTAIRE_SQL + this.idUser + "','" 
												+ this.idPost + "','"
												+ this.texte + "');");
			 stmt.close();
			 ajout = true;
			 return ajout;

		}catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		} 
	}
	public String recupProprietaire()
	{
		Connection connection = bdd.getInstance();
		Statement stmt;
		String pseudo="";
		try {
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(PROPRIETAIRE_COMMENTAIRE_SQL+" commentaire.id = "+this.id);
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

	
}
