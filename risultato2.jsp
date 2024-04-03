<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300..700&display=swap" rel="stylesheet">

<style>
	body {
		text-align: center;
		background-color: #ff8080;
		color: black;
		font-family: "Quicksand", sans-serif;
	}
	
	button {
		display: block;
		margin: 0 auto;
		background-color: #ff8080;
		border: 4px solid black;
		border-radius: 5px;
	}
	
	a {
		text-decoration: none;
		color: black;
		font-family: "Quicksand", sans-serif;
	}
</style>

<body>

<h2>ACCESSO NEGATO</h2>
<% String nome = request.getParameter("user"); %>
<p><%= nome %>, mi dispiace non hai le credenziali per poter accedere alla pagina</p>


<br><br>
<button><a href="index.html">Torna Indietro</a></button>

</body>
</html>