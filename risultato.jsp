<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inserimento prodotti</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300..700&display=swap" rel="stylesheet">

<style>
	
	body {
		background-color: #ccffcc;
		font-family: "Quicksand", sans-serif;
	}
	
	h1 {
		text-align: center;
		color: green;
		text-shadow: 3px 2px 3px white;
	}
	
	ul {
		width: 250px;
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

<h1> Nuovo prodotto inserito con successo! </h1>

<ul>
<li>Nome prodotto: <b>${prodotto.getNome()}</b> </li>
<li> Prezzo: ${prodotto.getPrezzo()} euro</li>
<li> Quantità: ${prodotto.getQuantità()}</li>
</ul>
<br><br>
<button><a href="index.html">Torna Indietro</a></button>

</body>
</html>