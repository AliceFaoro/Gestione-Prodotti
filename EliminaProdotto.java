package com;

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

/**
 * Servlet implementation class EliminaProdotto
 */

@WebServlet("/Remove")
public class EliminaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaProdotto() {
        super();
       
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
		
		String name = request.getParameter("nome");
		
		String removeQuery = "DELETE FROM prodotti WHERE nome = (?)";
		
				try {
					Connection conn = DriverManager.getConnection(url + dbName, user, password);
					
					PreparedStatement stmt = conn.prepareStatement(removeQuery);
					
					stmt.setString(1, name);
					
					stmt.executeUpdate();
					
				} catch (SQLException e) {

					System.out.println("Errore durante l'inserimento dei dati nella tabella 'prodotti':");
					e.printStackTrace();
				}
				
		request.setAttribute("name", name);
		request.getRequestDispatcher("/risultato3.jsp").forward(request, response);
	}

}
