package valot.mutez;

import java.io.IOException;
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

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/LoginServlet")
//the « LoginServlet » is called when the url 
//« http://localhost:8081/WebToDoList/LoginServlet » is entered in the browser.
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDBManager userDBManager;
	@Resource(name="jdbc/todolists")
	private DataSource dataSource;


	//D'abord, on regarde si des cookies sont présents dans le navigateur afin de compléter
	//automatiquement les champs username et password.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie [] cookies = request.getCookies();
		if(cookies!= null){
			for(Cookie cookie:cookies){
					if(cookie.getName().equals("username"))
						request.setAttribute("username", cookie.getValue()) ;
					if(cookie.getName().equals("password"))
						request.setAttribute("password", cookie.getValue()) ;
			}
		}
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		try {
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//S'exécute lorsque l'on clique sur le bouton login.
	//D'abord on récupère ce que le client a rentré dans les champs de texte username et password.
	//Ensuite on vérifie si ses identifiants et mdp sont bien dans la BDD.
	//Si c'est le cas, alors on crée une session dans laquelle on enregistre son ID afin de pouvoir récupérer 
	//ses données dans toutes les autres servlets à l'aide de la fonction fetchUserByID.
	//On crée ensuite le cookie (on ne le fait pas avant la vérification des credentials afin de ne pas créer un cookie
	//pour un utilisateur non enregistré dans notre BDD.
	//Enfin on redirige l'utilisateur vers la main page.
	//Si les identifiants sont erronés, alors l'utilisateur est renvoyé vers une page d'erreur qui affiche un message 
	//d'erreur (unique) et propose soit de se rediriger vers la page login pour retenter de se connecter, soit de se rediriger 
	//vers la page registration qui lui permettra de se créer un compte.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		try {
			if(CheckCredentials(username, password)) {
				//Création de la session 
				HttpSession session = request.getSession();
				User current_user = userDBManager.fetchUserByUsername(username);
				session.setAttribute("ID", current_user.getId());
				
				//Création du cookie pour username et password pour l'autocomplétion en cas de reconnexion (sous 24h)
				Cookie user_cookie = new Cookie("username", username);
				Cookie password_cookie = new Cookie("password",password);
				user_cookie.setMaxAge(60*60*24) ; 
				password_cookie.setMaxAge(60*60*24);
				response.addCookie(user_cookie) ;
				response.addCookie(password_cookie) ;
				
				response.sendRedirect("ToDoServlet");
			}
			else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		userDBManager = new UserDBManager(dataSource);
	}
	
	//Méthode simple qui permet de vérifier si un utilisateur appartient bien à la BDD.
	//On prend en paramètres le username et password de l'utilisateur et on va chercher
	//ce user avec la fonction fetchUserByUsernamePassword qui renvoie un user si la combinaison des 
	//deux existe. Sinon, elle renvoie null et dans ce cas on donne la valeur false a confirmed avant de le return.
	public boolean CheckCredentials(String username, String password) throws Exception {
		boolean confirmed=true;
		if(userDBManager.fetchUserByUsernamePassword(username,password)==null) {
			confirmed=false;
		}
		return confirmed;
	}
}

