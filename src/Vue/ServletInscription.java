package Vue;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/Inscription.jsp");
		dispatcher.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message;
		String contextpath = this.getServletContext().getContextPath();
		String prenom = request.getParameter("firstname");
		String nom = request.getParameter("lastname");
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		String mdp = request.getParameter("mdp");
		Utilisateur un = new Utilisateur(nom,prenom,pseudo,email,mdp);
		if(un.testPseudo())
		{
			message = "Ce Pseudo est déjà utilisé";
			request.setAttribute("error", message);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Inscription.jsp").forward(request, response);
		}
		else if(un.testEmail())
		{
			message ="L'addresse mail est déjà prise.";
			request.setAttribute("error", message);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Inscription.jsp").forward(request, response);
		}
		else if(nom.isEmpty() || prenom.isEmpty() || pseudo.isEmpty() || email.isEmpty() || mdp.isEmpty())
		{
			message = "Un champ de saisie n'a pas été rempli";
			request.setAttribute("error", message);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Inscription.jsp").forward(request, response);
		}
		else
		{
		un.addUser();
		response.sendRedirect(contextpath+"/ServletConnexion");
		}
		
		//this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response);
	}

}
