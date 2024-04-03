package com;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class Accedi
 */

@WebServlet("/Compra")
public class Accedi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accedi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String url = "jdbc:mysql://localhost:3306/"; 
		String dbName = "newDB"; 
		String user = "root"; 
		String password = "Topolino.16"; 
	
		
		String username = request.getParameter("user");
		String email = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		ServletContext context = getServletContext();
        
        context.setAttribute("email", email);
		
		
		ArrayList<Prodotto> productList = (ArrayList<Prodotto>) new ArrayList();

		String executeQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
		try {
			Connection conn = DriverManager.getConnection(url + dbName, user, password);
		    PreparedStatement stmt = conn.prepareStatement(executeQuery);
		    stmt.setString(1, username);
		    stmt.setString(2, pass);
		    ResultSet rs = stmt.executeQuery();
		    
		    if (rs.next()) {
		    	
		    	Connection conn1 = DriverManager.getConnection(url + dbName, user, password);
	             ResultSet rs1 = stmt.executeQuery("SELECT * FROM prodotti");

	            while (rs1.next()) {
	                String nome = rs1.getString("nome");
	                int prezzo = rs1.getInt("prezzo");
	                int quantità = rs1.getInt("quantità");
	                Prodotto prodotto = new Prodotto();
	                prodotto.setNome(nome);
	                prodotto.setPrezzo(prezzo);
	                prodotto.setQuantità(quantità);
	                productList.add(prodotto);
	            }
		        request.setAttribute("lista", productList);
		        request.getRequestDispatcher("/compra.jsp").forward(request, response);
		    } else {
		       
		        request.getRequestDispatcher("/risultato2.jsp").forward(request, response);
		    }
		} catch (SQLException e) {
		    System.out.println("Errore durante l'esecuzione della query:");
		    e.printStackTrace();
		}
	}

}
