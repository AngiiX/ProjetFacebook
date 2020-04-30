<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Créer un post</title>
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
            <h1>Nouveau post</h1>
        </div>

        <div class="annonce mx-auto p-2">
            <p class="commT"><b>Votre poste :</b></p>

            <form action="" method="post">
                <textarea class="creerComm" type="text" name="message"></textarea><br>
                <input type="submit" value="Publier" name="publier">
            </form>

        </div>
    </main>
    <footer></footer>
    
</body>
</html>