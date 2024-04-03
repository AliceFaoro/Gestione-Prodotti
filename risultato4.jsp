<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acquista prodotto</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300..700&display=swap" rel="stylesheet">
	
	<style>
	
		body {
			background-color: #ccffcc;
			font-family: "Quicksand", sans-serif;
			text-align: center;
		}
		
		h1 {
			text-align: center;
			color: green;
			text-shadow: 3px 2px 3px white;
		}
		
		ul {
			width: 100px;
			margin: 0 auto;
			text-align: left;
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

	<h1> Prodotti acquistato con successo! </h1>
	
	<h3>Prodotti acquistati: </h3>
	
	<% 
	String [] lista = (String [])request.getAttribute("ordine");
	%>
	
	<ul>
	<%
	for (int i = 0; i < lista.length; i++)  {
		out.print("<li>" + lista[i] + " </li>");
		out.print("<br>");
	
	}
	
	%>
	</ul>
	
	<p>Per un totale di ${prezzo} euro.</p>
	
	<br><br>
	<button id="button"><a href="index.html">Torna Indietro</a></button>


</body>
</html>