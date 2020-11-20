package valot.mutez;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class DeleteTodoServlet
 */
@WebServlet("/DeleteTodoServlet")
public class DeleteTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDBManager userDBManager;
	@Resource(name="jdbc/todolists")
	private DataSource dataSource;
       
	public void init(ServletConfig config) throws ServletException {
		super.init();
		userDBManager = new UserDBManager(dataSource);
	}

	//cette méthode s'exécute à partir du moment où l'utilisateur clique sur le lien Delete de la main page 
	//On récupère d'abord l'id du current user pour créer le todo qui prend en paramètre la description et l'id du créateur
	//On récupère aussi la description du todo à partir des paramètres de la request (paramètres envoyés à partir du main-jsp)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute( "ID" );
		String description_todo= request.getParameter("description");
		Todo todo = new Todo(description_todo,id);
		try {
			userDBManager.Delete(todo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("ToDoServlet");
	}
	
	//Pas besoin de méthode doPost car on a pas de jsp associé à cette servlet et donc aucun bouton ou lien
	//ne peut appeler cette méthode.

}
