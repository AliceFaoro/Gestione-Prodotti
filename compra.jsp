<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Compra</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300..700&display=swap" rel="stylesheet">

	<style>
	
	body {
		background-color: #ffe6ff;
		text-align: center;
		font-family: "Quicksand", sans-serif;
	}
	
	form {
		border: 5px solid purple;
		border-radius: 10px;
		width: 300px;
		padding: 10px;
		background-color: #f2e6ff;
		display: block;
		margin: 0 auto;
	}
	
	#button {
		background-color: purple;
		color: white;
		font-family: "Quicksand", sans-serif;
	}
	</style>
</head>
<body>

	<%@ page import = "java.util.ArrayList"%>
	<%@ page import = "com.Prodotto"%>
	<%
		ArrayList<Prodotto> lista= (ArrayList)request.getAttribute("lista");
		String actionUrl = "ordina";
		
		if (lista == null){
			out.print("E' null");
		} else {
			out.print("<form action='" + actionUrl + "' method='post'>");
			for (int i = 0; i < lista.size(); i++){
				out.print("<b>" + lista.get(i).getNome() + "</b>");
				out.print(", prezzo: ");
				out.print(lista.get(i).getPrezzo() + " euro");
				
				out.print("<input type='checkbox' id='ordini' name='ordini' value='" + lista.get(i).getNome() + "'>");
				
				out.print("<hr>");
			}
		out.print("<input type='submit' value='Compra' id='button'>");
		out.print("</form>");
				
		}
	
	%>



</body>
</html>