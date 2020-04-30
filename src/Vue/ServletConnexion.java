package Vue;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Utilisateur;

/**
 * Servlet implementation class MaServlet
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp");
		dispatcher.forward(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		session.invalidate();
		String message;
		String contextpath = this.getServletContext().getContextPath();
		String email = request.getParameter("email");
		String mdp = request.getParameter("mdp");
		Utilisateur u = new Utilisateur(null,null,null,email,mdp);
		//request.getSession();
		if(request.getParameter("bouton").equals("Connecter"))
		{
			if(u.identification())
			{
				// connexion bonne, on attribue une session
				session = request.getSession();
				session.setAttribute("id", u.getId());
				session.setAttribute("prenom", u.getPrenom());
				session.setAttribute("nom", u.getNom());
				session.setAttribute("pseudo", u.getPseudo());
				session.setAttribute("email", u.getEmail());
				session.setAttribute("mdp", u.getMdp());
				// redirection vers la page des posts
				response.sendRedirect(contextpath+"/ServletAccueil");
				
			}
			else
			{
				// connexion pas bonne, on retourne au formulaire
				message="Votre adresse mail ou votre mot de passe est incorrecte";
				request.setAttribute("error", message);
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response);
			}
			
		}
		else
		{
			response.sendRedirect(contextpath+"/ServletInscription");	
		}
		
	}

}
