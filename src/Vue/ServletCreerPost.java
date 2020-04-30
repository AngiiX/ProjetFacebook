package Vue;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Post;

/**
 * Servlet implementation class ServletCreerPost
 */
@WebServlet("/ServletCreerPost")
public class ServletCreerPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCreerPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/CreerPost.jsp");
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
		String texte = request.getParameter("message");
		Post p1 = new Post(id,texte);
		p1.addPost();
		response.sendRedirect(contextpath+"/ServletAccueil");
	}

}