package com;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Servlet implementation class CompraProdotto
 */

@WebServlet("/ordina")
public class CompraProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompraProdotto() {
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
		String password = "****"; 

		String[] ordiniSelezionati = request.getParameterValues("ordini");
		request.setAttribute("ordine", ordiniSelezionati);
		int totale=0;
		
		 for (int i = 0; i < ordiniSelezionati.length; i++) {
		    	String name = ordiniSelezionati[i];
					
				String updateQuery = "UPDATE prodotti SET quantità = quantità-1 WHERE nome = (?)";
					
				try {
					Connection conn = DriverManager.getConnection(url + dbName, user, password);
						
					PreparedStatement stmt = conn.prepareStatement(updateQuery);
						
					stmt.setString(1, name);
						
					stmt.executeUpdate();
						
				} catch (SQLException e) {
			
					System.out.println("Errore");
					e.printStackTrace();
				}
				
				String selectQuery = "SELECT prezzo FROM prodotti WHERE nome = ?";
				try {
					Connection conn = DriverManager.getConnection(url + dbName, user, password);
						
					PreparedStatement stmt = conn.prepareStatement(selectQuery);
						
					stmt.setString(1, name);
						
					ResultSet rs1 = stmt.executeQuery();
					if (rs1.next())  {
				        int prezzo = rs1.getInt("prezzo");
				        totale += prezzo;
					}
					 
				} catch (SQLException e) {
			
					System.out.println("Errore");
					e.printStackTrace();
				}
				
		}
		 
		String prodotti = "";
		   for (String prod: ordiniSelezionati) {
		   	prodotti += (prod + " ");
		}
		    
		request.setAttribute("prodotti", prodotti);
		request.setAttribute("prezzo", totale);		

		request.getRequestDispatcher("/risultato4.jsp").forward(request, response);
			
		
		ServletContext context = getServletContext();
		
		String email = (String) context.getAttribute("email");
		
		//CODICE PER INVIARE EMAIL ALL'UTENTE CON LA LISTA DEI PRODOTTI COMPRATI
		// Questo blocco di codice imposta le proprietà necessarie per connettersi al server SMTP di Outlook 
    	//utilizzando il protocollo TLS v1.2 per la crittografia e la porta 587.
	   
        Properties props = new Properties();
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Specifica il protocollo TLS
        props.put("mail.smtp.host", "smtp.office365.com"); // Server SMTP di Outlook
        props.put("mail.smtp.port", "587"); // Porta SMTP
        props.put("mail.smtp.auth", "true"); // Autenticazione richiesta
        props.put("mail.smtp.starttls.enable", "true"); // Abilita STARTTLS per la crittografia

        // Viene creata una sessione di autenticazione utilizzando le proprietà precedentemente configurate. 
        //Qui viene richiesta l'autenticazione utilizzando l'indirizzo email e la password forniti.
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("alicefaoro@live.it", "*****"); // Inserisci qui il tuo indirizzo email e la tua password di Outlook
            }
        });

        try {
            //Viene creato un oggetto MimeMessage utilizzando la sessione precedentemente configurata. 
        	//Questo oggetto rappresenterà l'email da inviare.
            Message message = new MimeMessage(session);
            
            // Vengono impostati mittente, destinatario, oggetto e corpo del messaggio dell'email utilizzando
            //i metodi dell'oggetto MimeMessage.
            message.setFrom(new InternetAddress("alicefaoro@live.it")); // Mittente Outlook
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); // L'indirizzo del destinatario
            message.setSubject("Acquisto andato a buon fine!"); //Oggetto Email
            String htmlBody = "<html><head>" +
  				  "<meta charset='UTF-8'>" +
  				  "<style>" +
  				  	"h1 {color: red;}"+
  				  	"body {padding: 10px ; background-color: pink}"+
  				  	"p {color: white;}" +
  				  "</style></head><body>" +
  				  "<h1>Riepilogo Acquisto </h1>" +
  				  "<p id='p1'>Prodotti acquistati: " + prodotti + "</p>" +
  				  "<p id='p2'>Totale pagato: " + totale + " euro.</p>" +
  				  "</body></html>";
  
            message.setContent(htmlBody, "text/html");

            //Viene inviata l'email utilizzando il metodo send del Transport, passando l'oggetto 
            //MimeMessage creato in precedenza.
            Transport.send(message);

            System.out.println("Email inviata con successo.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Errore durante l'invio dell'email.");
        }
	
		
	} 

}
