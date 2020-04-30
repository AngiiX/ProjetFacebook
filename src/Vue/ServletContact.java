package Vue;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Relation;
import DAO.Utilisateur;

/**
 * Servlet implementation class ServletContact
 */
@WebServlet("/ServletContact")
public class ServletContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletContact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/Contact.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String contextpath = this.getServletContext().getContextPath();
		int id = (int) session.getAttribute("id");
		String email =(String) session.getAttribute("email");
		String mdp =(String) session.getAttribute("mdp");
		String nom =(String) session.getAttribute("nom");
		String prenom =(String) session.getAttribute("prenom");
		String pseudo =(String) session.getAttribute("pseudo");
		Utilisateur u1 = new Utilisateur(nom,prenom,pseudo,email,mdp);
		Relation r1 = new Relation(id,0);
		String ps = request.getParameter("ps");
		if(request.getParameter("valider").equals("valider"))
		{
			
			r1.addRelation(ps);
			response.sendRedirect(contextpath+"/ServletCompte");
		}
		else
		{
			
		
		response.sendRedirect(contextpath+"/ServletAccueil");
		}
	}

}
