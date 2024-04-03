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
import java.sql.SQLException;
import java.util.ArrayList;

import com.Prodotto;

/**
 * Servlet implementation class GestisciProdotto
 */

@WebServlet("/Submit")
public class GestisciProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestisciProdotto() {
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
		
		String url = "jdbc:mysql://localhost:3306/"; // URL del database
		String dbName = "newDB"; // Nome del database
		String user = "root"; // Nome utente
		String password = "Topolino.16"; //password
		
		Prodotto p1 = new Prodotto();
		String nome = request.getParameter("name");
		int prezzo = Integer.parseInt(request.getParameter("prezzo"));
		int quantità = Integer.parseInt(request.getParameter("quantità"));
		p1.setNome(nome);
		p1.setPrezzo(prezzo);
		p1.setQuantità(quantità);
		
	
		request.setAttribute("prodotto", p1);
		request.getRequestDispatcher("/risultato.jsp").forward(request, response);
		
		
		
		String insertQuery = "INSERT INTO prodotti (nome, prezzo, quantità) VALUES (?, ?, ?)";
		try {
			Connection conn = DriverManager.getConnection(url + dbName, user, password);
			
			PreparedStatement stmt = conn.prepareStatement(insertQuery);
			
			stmt.setString(1, nome);
			stmt.setInt(2, prezzo);
			stmt.setInt(3, quantità);
			stmt.executeUpdate();
			
		} catch (SQLException e) {

			System.out.println("Errore durante l'inserimento dei dati nella tabella 'prodotti':");
			e.printStackTrace();
		}
		
		
	}

}
