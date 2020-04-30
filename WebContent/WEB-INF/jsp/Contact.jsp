<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "javax.servlet.http.HttpSession" %>
    <%@ page import = "DAO.*" %>
    <%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Contacts</title>
    <style></style>
</head>
<body>
<header class="p-2 bg-warning text-white d-flex justify-content-between">
        <div class="logo">
        </div>
        <div class="h2">
            <p class="pt-4 ">MiniBlog</p>
        </div>
        <div class="" onclick="window.location.href='./ServletConnexion';">
            <p class="border border-dark text-muted p-2">Se déconnecter</p>
        </div>
    </header>
<% int id = (int) session.getAttribute("id");
String prenom = (String) session.getAttribute("prenom");
String nom = (String) session.getAttribute("nom");
String pseudo = (String) session.getAttribute("pseudo");
String email = (String) session.getAttribute("email");
Utilisateur user1 = new Utilisateur(id,prenom,nom,pseudo,email,null);
ArrayList<Post> actu = user1.actuUser();
ArrayList<Relation> relation = user1.possedeRelation();
%>
    <main>
        <div class="d-flex bd-highlight">
            <div class="p-2 flex-fill bd-highlight border border-dark " onclick="window.location.href='./ServletAccueil';">
                <p class="text-center pt-2 mb-1">Actualité</p>
            </div>
            <div class="p-2 flex-fill bd-highlight border border-dark" onclick="window.location.href='./ServletContact';">
                <p class="text-center pt-2 mb-1">Contact</p>
            </div>
            <div class="p-2 flex-fill bd-highlight border border-dark" onclick="window.location.href='./ServletCompte';">
                <p class="text-center pt-2 mb-1">Mon compte</p>
            </div>
        </div>

        <div class="espace pt-4">
            <h1>Gérer mes contacts</h1>
        </div>

        <div class="d-flex justify-content-between ajout">

            <div class="ml-5">
                <h2>Ajouter</h2>
                <form action="" method="post">
                    <label class="mr-1">Pseudo : </label>
                    <input type="text" name="ps"><br>
                    <input type="submit" value="valider" name="valider">
                </form>
            </div>
        </div>

        <div class="Samis mx-auto">
            <h2>Liste des contacts</h2>
            <% ArrayList<String>uti = new ArrayList<String>();
            for(Relation r1 : relation)
            	{
            	uti = r1.recupProprietaire1(id);}
           	for(int i = 0; i< uti.size();i++)
           	{
           		
            	%>
            <div class="amis d-flex justify-content-around ">
           	<p><%
           	out.print(uti.get(i));
           	%></p><a href="contact.html">Supprimer</a>
			</div>
            <%} %>
            
        </div>

    </main>
    <footer></footer>
</body>
</html>