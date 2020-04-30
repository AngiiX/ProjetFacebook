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
    <title>Compte</title>
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
<%int id = (int) session.getAttribute("id");
String prenom = (String) session.getAttribute("prenom");
String nom = (String) session.getAttribute("nom");
String pseudo = (String) session.getAttribute("pseudo");
String email = (String) session.getAttribute("email");
Utilisateur user1 = new Utilisateur(id,prenom,nom,pseudo,email,null);
ArrayList<Post> actu = user1.actuUser();
int idTour =0;
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

        <div class="Samis mx-auto">
            <h2>Information du compte</h2>
            <div class="">
                <p><span>Nom : </span><span><% out.print(user1.getNom()); %></span></p>
            </div>
            <div class="">
                <p><span>Prénom : </span><span><% out.print(user1.getPrenom()); %></span></p>
            </div>
            <div class="">
                <p><span>Pseudo : </span><span><% out.print(user1.getPseudo()); %></span></p>
            </div>
            <div class="">
                <p><span>Email : </span><span><% out.print(user1.getEmail()); %></span></p>
            </div>
            <div class="" onclick="window.location.href='./ServletConnexion';"><a href="./ServletConnexion">Se déconnecter</a></div>
        </div>

    </main>
    <footer></footer>
</body>
</html>