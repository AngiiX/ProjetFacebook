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
    <title>Actualité</title>
    <style></style>
</head>
<body>
<%int id = (int) session.getAttribute("id");
String prenom = (String) session.getAttribute("prenom");
String nom = (String) session.getAttribute("nom");
String pseudo = (String) session.getAttribute("pseudo");
String email = (String) session.getAttribute("email");
Utilisateur user1 = new Utilisateur(id,prenom,nom,pseudo,email,null);
ArrayList<Post> actu = user1.actuUser();
%>
	<header class="p-2 bg-warning text-white d-flex justify-content-between">
        <div class="logo">
        </div>
        <div class="h2">
            <p class="pt-4 ">MiniBlog</p>
        </div>
        <div class="" >
            <p onclick="window.location.href='./ServletConnexion';" class="border border-dark text-muted p-2">Se déconnecter</p>
        </div>
    </header>

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

       <div class="espace pt-4 d-flex justify-content-between">
            <h1>Votre fil d'actualité</h1>
            <div>
                <a href="./ServletCreerPost" class="display-4">Créer un post</a>
            </div>
            <div></div>
            <div></div>
        </div>

       
           <% for(Post p1 : actu)
           {%>
        	   <div class="annonce mx-auto p-2">
               <p class="qui"><u><% out.print(p1.recupProprietaire()); %></u></p>
        	   <p class="texte"><% out.print(p1.getTexte());  %> </p> 
        	   <p class="commT"><b>Commentaires :</b></p>
              
               <%
               for(Commentaire c1 : p1.possedeCommentaire())
               {
              	 
               
               %>  <div class="comm p-2">  <p class=""><% out.print(c1.getTexte()); %></p>
             
                <div class="d-flex justify-content-end mr-3 mb-2">
                    <p class="commNom"><b><% out.print(c1.recupProprietaire()); %></b></p>
                </div>

            </div>
            <% } %> 
            <div class="d-flex justify-content-end mr-3 mb-2">
                <a href="./ServletCommentaire?post=<%=p1.getId()%>&pseudo=<%=p1.recupProprietaire() %>">Ajouter un commentaire</a>
            </div>
            </div>
   			<% } %>         
        

    </main>

    <footer>
    </footer>
</body>
</html>