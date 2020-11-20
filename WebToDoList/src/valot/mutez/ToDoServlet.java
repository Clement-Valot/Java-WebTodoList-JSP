package valot.mutez;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


@WebServlet("/ToDoServlet")
public class ToDoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserDBManager userDBManager;
	@Resource(name="jdbc/todolists")
	private DataSource dataSource;
	User current_user = null;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute( "ID" );
		try {			
			current_user = userDBManager.fetchUserById(id);
			request.setAttribute("USER", current_user);
			
			todolist(request, response);
			

			request.getRequestDispatcher("/main-page.jsp").forward(request, response);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	//Pas de méthode doPost car pas d'action avec bouton sur cette page (que des liens )

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		userDBManager = new UserDBManager(dataSource);
	}
	
	private void todolist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Todo> todos = new ArrayList<Todo>();
		if (current_user.isInstructor()==true) {
			todos = userDBManager.getAllTodos(current_user.getId());
		}
		else {
			todos = userDBManager.getTodos(current_user.getId());
		}
		request.setAttribute("TODO_LIST", todos);
	}
}

