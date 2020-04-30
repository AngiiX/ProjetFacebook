<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Creation de compte</title>
    <style></style>
</head>
<body>
<header class="p-2 bg-warning text-white d-flex justify-content-between">
        <div class="logo">
        </div>
        <div class="h2">
            <p class="pt-4 mr-5">MiniBlog</p>
        </div>
        <div>
        </div>
    </header>
     <main>
        <form action="" method="POST" class="connect mx-auto mt-5">
            <h2 class="mt-2 mb-4">Création de compte</h2>
            <div class="creer">
            <%if(request.getAttribute("error")!=null){ %>
              <p class="text-danger">  <% out.print(request.getAttribute("error")); }%> </p>
            <p class="d-flex justify-content-between">
                <label>Nom : </label>
                <input type="text" name="lastname">
            </p>
             <p class="d-flex justify-content-between">
                <label>Prénom : </label>
                <input type="text" name="firstname">
            </p>
             <p class="d-flex justify-content-between">
                <label>Pseudo : </label>
                <input type="text" name="pseudo">
            </p>
             <p class="d-flex justify-content-between">
                <label>Email : </label>
                <input type="text" name="email">
            </p>
             <p class="d-flex justify-content-between">
                <label>Mot de passe : </label>
                <input type="text" name="mdp">
            </p>
            </div>
                <input type="submit" value="Valider" name="valider" class="ml-5">
            
        </form>
    </main>
<footer></footer>
<body>	
	
</body>
</html>