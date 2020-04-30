<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Connexion</title>
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
            <div>
                <h2 class="mt-2 mb-4">Se connecter</h2>
                <%if(request.getAttribute("error")!=null){ %>
              <p class="text-danger">  <% out.print(request.getAttribute("error")); }%> </p>
                <p>
                    <label>Adresse mail : </label>
                    <input type="text" name="email" id="email" value="mattis@gmail.com">
                </p>
                <p>
                    <label>Mot de passe : </label>
                    <input type="password" name="mdp" id ="mdp" value="mattis">
                </p>
                <div class="d-flex justify-content-end mr-5">
                    <form action="/ServletInscription" method="POST">
                    	<input type="submit" value="Creer" name="bouton">
                    </form><br>
                </div>
                <input type="submit" value="Connecter" name="bouton">
            </div>
        </form>
    </main>

    <footer>
    </footer>
	
</body>
</html>