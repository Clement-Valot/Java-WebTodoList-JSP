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


@WebServlet("/EditTodoServlet")
public class EditTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDBManager userDBManager;
	@Resource(name="jdbc/todolists")
	private DataSource dataSource;
	
	User current_user = null; //variable globale de current user pour accéder à ses attributs partout dans la classe
	String description_todo = null; //variable globale de la description du todo (avant modification)
	Todo todo = null; //variable globale du todo
	

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		userDBManager = new UserDBManager(dataSource);
	}

	//Récupère la session pour afficher le message de bienvenue
	//Récupère la valeur de la description du todo stockée dans les paramètres de la request (envoyé à partir du main-jsp)
	//et la place dans la zone de texte à modifier
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute( "ID" );
		description_todo= request.getParameter("description");
		request.setAttribute("description", description_todo);
		try {			
			current_user = userDBManager.fetchUserById(id);
			request.setAttribute("USER", current_user);
			if(current_user.isInstructor()) {
				request.getRequestDispatcher("/edit-Todo.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.print("erreur");
			e1.printStackTrace();
		}
	}

	//cette méthode s'applique au moment de cliquer sur le bouton save.
	//Elle envoie une requête sql update à la BDD qui va changer la description de tous les todos ayant la même
	//description que description_todo (la description avant modification) ainsi que le même id creator (au cas
	//où deux professeurs auraient donné le même todo car un professeur ne doit pouvoir altéré que les todos qu'il
	//a crées).
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String new_Description = request.getParameter("description");
		Todo todo = new Todo(description_todo,current_user.getId());
		try {
			userDBManager.Edit(todo, new_Description);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("ToDoServlet");

	}

}
