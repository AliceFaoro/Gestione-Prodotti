<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista Prodotti</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300..700&display=swap" rel="stylesheet">

<style>
	body {
		background-color: #ccffcc;
		text-align: center;
		font-family: "Quicksand", sans-serif;
	}
	li {
		width: 150px;
		margin: 0 auto;
	}
	button {
		display: block;
		margin: 0 auto;
		background-color: #ccffcc;
		border: 4px solid green;
		border-radius: 5px;
	}
	
	a {
		text-decoration: none;
		color: black;
		font-family: "Quicksand", sans-serif;
	}

</style>

</head>
<body>


<%@ page import = "java.util.ArrayList"%>
<%@ page import = "com.Prodotto"%>

	<h2>Lista Prodotti Aggiunti:</h2>
	<br>
	<%
	ArrayList<Prodotto> lista = (ArrayList<Prodotto>) request.getAttribute("lista");

	out.print("<ol>");
	for (int i = 0; i < lista.size(); i++) {

		out.print("<li> Nome: <b>" + lista.get(i).getNome() + "</b><br>");
		out.print("Prezzo: " + lista.get(i).getPrezzo() + " euro<br>");
		out.print("Quantità: " + lista.get(i).getQuantità() + "</li><br>");
	}
	out.print("</ol>");
	%>

<br><br>
<button><a href="index.html">Torna Indietro</a></button>

</body>
</html>