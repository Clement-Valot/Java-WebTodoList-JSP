package valot.mutez;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class AddToDoServlet
 */
@WebServlet("/AddToDoServlet")
public class AddToDoServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private UserDBManager userDBManager;
	@Resource(name="jdbc/todolists")
	private DataSource dataSource;
	
	// On crée une variable globale current_user pour acceder a ses attributs partout dans la classe
	User current_user = null;
    
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		userDBManager = new UserDBManager(dataSource);
	}

	// Récupère la session pour afficher le message de bienvenue en haut de page
	// Récupère le current user avec la session
	// Récupère la liste de tous les étudiants pour (plus tard) donner la possibilité au professeur de choisir à qui donner la tâche
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute( "ID" );
		try {			
			current_user = userDBManager.fetchUserById(id);
			request.setAttribute("USER", current_user);
			
			List<User> users = userDBManager.getStudents();
			request.setAttribute("LIST_USERS", users);
			
			if(current_user.isInstructor()) {
				// Renvoie toutes les données au add-todo jsp
				request.getRequestDispatcher("/add-todo.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	// S'applique au moment où l'on clique sur le bouton save
	// Permet de créer une nouvelle tâche par un professeur
	// D'abord on recupere la description entrée par le porfesseur
	// Ensuite on regarde quels sont les élèves concernés par la tâche
	// Enfin on rajoute la nouvelle tahce dans la base de données à l'aide de la fonction AddTodo
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Recupere la description
		String description= request.getParameter("description");
		Todo todo = new Todo(description, current_user.getId());
		
		// Recupere la liste des students qui ont été cochés par le professeur en string
		String[] selectedStudentIds = request.getParameterValues("selected");
		
		// Recupere la liste des students qui ont été cochés par le professeure en int
		List<Integer> list_user = new ArrayList<Integer>();
		for(int i=0; i<selectedStudentIds.length;i++) {
			list_user.add(Integer.parseInt(selectedStudentIds[i]));
        }
		try {
			// On ajoute la tâche
			userDBManager.AddTodo(todo, list_user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Renvoie sur la page Add à la fin de l'opération
		response.sendRedirect("AddToDoServlet");
	}

}
