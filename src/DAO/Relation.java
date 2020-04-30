package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Bdd.bdd;

public class Relation 
{
	int id;
	int idUser1;
	int idUser2;
	
	private static String FETCH_RELATION_SQL = "SELECT id, idUser1, idUser2 FROM relation ";
	private static String ADD_RELATION_SQL = "INSERT INTO relation(id,idUser1,idUser2) VALUES ('";
	private static String PROPRIETAIRE_RELATION_SQL = "SELECT DISTINCT utilisateur.* FROM utilisateur INNER JOIN relation as r1 on utilisateur.id = r1.idUser1 INNER JOIN relation as r2 on utilisateur.id = r2.idUser2 WHERE ";
	private static String PRO_RELATION_SQL = "SELECT * FROM utilisateur ";
	private static String AJOUTRELATION_RELATION_SQL = "INSERT INTO relation(id,idUser1,idUser2) VALUES (NULL,'";

	
	public Relation(int id_, int idUser1_, int idUser2_)
	{
		
		this.id = id_;
		this.idUser1 = idUser1_;
		this.idUser2 = idUser2_;
		
	}
	public Relation(int idUser1_, int idUser2_)
	{
		this.idUser1 = idUser1_;
		this.idUser2 = idUser2_;
	}
	public int getId() {
		return id;
	} 
	public int getIdUser1() {
		return idUser1;
	}
	public int getIdUser2() {
		return idUser2;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIdUser1(int idUser1) {
		this.idUser1 = idUser1;
	}
	public void setIdUser2(int idUser2) {
		this.idUser2 = idUser2;
	}
	public ArrayList<Relation> recupRelation()
	{
		ArrayList<Relation> listRelation = new ArrayList<Relation>();
		Connection connection = bdd.getInstance();
		Statement stmt;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(FETCH_RELATION_SQL);
			// Loop over the database result set and create the
			// user objects.
			while (rs.next()) {
				
				Relation r = new Relation(rs.getInt("id"), rs.getInt("idUser1"), rs.getInt("idUser2"));
				listRelation.add(r);
				
			}
			// Free resources
			rs.close();
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		return listRelation;
	}
	public boolean addRelationPseudo(int id, String pseudo)
	{
		boolean ajout = false;
		Connection connection = bdd.getInstance();
		Statement stmt;
		
		try {
			
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(FETCH_RELATION_SQL);
			// Loop over the database result set and create the
			// user objects.
			while (rs.next()) {
			}

			 stmt.executeUpdate(ADD_RELATION_SQL+ this.idUser1 + "','" 
												+ this.idUser2 + "');");
			 
			 rs.close();
			 stmt.close();
			 ajout = true;
			 return ajout;

		}catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		} 
	}
	public String recupProprietaire(int id)
	{
		Connection connection = bdd.getInstance();
		Statement stmt;
		String pseudo="";
		String prenom="";
		String nom="";
		try {
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(PROPRIETAIRE_RELATION_SQL+" r1.idUser2 = "+id +" OR r2.idUser1 = "+id);
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
	public ArrayList<String> recupProprietaire1(int id)
	{
		Connection connection = bdd.getInstance();
		Statement stmt;
		ArrayList<String> nbrUti = new ArrayList<String>();
		String pseudo="erreur";
		try {
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(PROPRIETAIRE_RELATION_SQL+" r1.idUser2 = "+ id + " OR r2.idUser1 = "+ id);
			// Loop over the database result set and create the
			
		
			// user objects.
			while(rs.next())
			{
				Utilisateur u2 = new Utilisateur(rs.getString("nom"),rs.getString("prenom"),rs.getString("pseudo"),rs.getString("email"),rs.getString("mdp"));
				nbrUti.add(rs.getString("pseudo"));
				
			}
						 
			 rs.close();
			 stmt.close();

		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return nbrUti;
	}
	
	public boolean addRelation(String pseudo)
	{
		boolean ajout = false;
		Connection connection = bdd.getInstance();
		Statement stmt;
		
		try {
			stmt = connection.createStatement();
			String requete = "INSERT INTO relation (id, idUser1, idUser2) VALUES (NULL, '";
					requete = requete + this.idUser1;
					requete = requete + "', (SELECT id FROM utilisateur WHERE pseudo LIKE '";
					requete = requete + pseudo;
					requete = requete + "') ) ";
					
			stmt.executeUpdate(requete); //
			// Loop over the database result set and create the
			// user objects.
			/* stmt.executeUpdate(AJOUTRELATION_RELATION_SQL
												+ this.id + "', (SELECT id FROM utilisateur WHERE pseudo LIKE '" 
												+ pseudo + "'));");*/
		//	 NULL, 'idSession', (SELECT id FROM `utilisateur` WHERE pseudo LIKE \"lePseudo\") )";
			 
			 
			 stmt.close();
			 ajout = true;
			 return ajout;

		}catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		} 
	}
	
	
}