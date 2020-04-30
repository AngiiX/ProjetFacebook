package Vue;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Commentaire;

/**
 * Servlet implementation class ServletCommentaire
 */
@WebServlet("/ServletCommentaire")
public class ServletCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCommentaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String postid = request.getParameter("post");
		String postname = request.getParameter("pseudo");
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/commentaire.jsp?post="+postid+"&pseudo="+postname);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String contextpath = this.getServletContext().getContextPath();
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		String postid = request.getParameter("idpost");
		System.out.println("post id : " +postid);
		int posid = Integer.parseInt(postid);
		System.out.println("posid : "+posid);
		String texte = request.getParameter("message");
		Commentaire c1 = new Commentaire(id,posid,texte);
		c1.addCommentaire();
		response.sendRedirect(contextpath+"/ServletAccueil");
	}

}
