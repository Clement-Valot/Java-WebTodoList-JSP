package valot.mutez;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

//helper class
public class UserDBManager {
	private DataSource dataSource;
	
	public UserDBManager(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	// Permet de récupérer les informations d'un user à partir de son id
	// Utilisé dans Edit, Add, Todo
	// retourne un user
	public User fetchUserById(int id) throws Exception {
		Connection myConn=null; //Pour créer la connexion avec la database
		Statement myStmt = null; //Pour envoyer la requête sql
		ResultSet myRs= null; //Pour récupérer les données renvoyées par sql
		User user = null;
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from user where id_user ="+id+";";
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()){
				String firstName=myRs.getString("first_name");
				String lastName=myRs.getString("last_name");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				boolean instructor = myRs.getBoolean("instructor");
				user= new User(id,firstName,lastName,username, password, instructor);
			}	
			return user;
		} 
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	// Permet de récupérer les informations d'un user à partir de son username
	// Utilisé dans Register, Login 
	// retourne un user
	public User fetchUserByUsername(String username) throws Exception {
		Connection myConn=null; //Pour créer la connexion avec la database
		Statement myStmt = null; //Pour envoyer la requête sql
		ResultSet myRs= null; //Pour récupérer les données renvoyées par sql
		User user = null;
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from todolists.user where username='"+username+"';";
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()){
				int id=myRs.getInt("id_user");
				String firstName=myRs.getString("first_name");
				String lastName=myRs.getString("last_name");
				String password = myRs.getString("password");
				boolean instructor = myRs.getBoolean("instructor");
				user= new User(id,firstName,lastName,username, password, instructor);
			}	
			return user;
		} 
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	// Permet de récupérer les informations d'un user à partir de son username et son password
	// Utilisé dans Todo, Login
	// retourne un user
	public User fetchUserByUsernamePassword(String username, String password) throws Exception {
		Connection myConn=null; //Pour créer la connexion avec la database
		Statement myStmt = null; //Pour envoyer la requête sql
		ResultSet myRs= null; //Pour récupérer les données renvoyées par sql
		User user = null;
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from todolists.user where username='"+username+"' and password='"+password+"';";
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()){
				int id=myRs.getInt("id_user");
				String firstName=myRs.getString("first_name");
				String lastName=myRs.getString("last_name");
				boolean instructor = myRs.getBoolean("instructor");
				user= new User(id,firstName,lastName,username, password, instructor);
			}	
			return user;
		} 
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	// Permet de créer la liste des taches que doit faire un élève
	// Utilisé dans Todo
	// Prend en paramètre l'id de l'élève
	// Retourne la liste des tâches
	public List<Todo> getTodos(int id_user) throws Exception {
		Connection myConn=null; //Pour créer la connexion avec la database
		Statement myStmt = null; //Pour envoyer la requête sql
		ResultSet myRs= null; //Pour récupérer les données renvoyées par sql
		List<Todo> todos = new ArrayList<Todo>();
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from todolists.todos where id_user="+id_user+";";
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()){
				int id=myRs.getInt("id");
				String description=myRs.getString("description");
				int id_creator=myRs.getInt("id_creator");
				Todo todo= new Todo(id,description, id_creator,id_user);
				todos.add(todo);
			}	
			return todos;
		} 
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	// Permet de créer la liste des taches qu'a créé un professeur
	// Utilisé dans Todo
	// Prend en paramètre l'id du professeur
	// Retourne la liste des tâches
	public List<Todo> getAllTodos(int id_creator) throws Exception {
		Connection myConn=null; //Pour créer la connexion avec la database
		Statement myStmt = null; //Pour envoyer la requête sql
		ResultSet myRs= null; //Pour récupérer les données renvoyées par sql
		List<Todo> todos = new ArrayList<Todo>();
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select distinct description from todolists.todos where id_creator =" + id_creator + ";";
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()){
				String description=myRs.getString("description");
				Todo todo= new Todo(description,id_creator);
				todos.add(todo);
			}	
			return todos;
		} 
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	// Permet de rajouter un user dans la base de données
	// Utilisé dans Register
	// Prend en paramètre un user
	public void Add(User user) throws Exception{
		Connection myConn=null; //Pour créer la connexion avec la database
		PreparedStatement myStmt = null; //Pour envoyer la requête sql
		try {
			myConn = dataSource.getConnection();
			String sql= "INSERT INTO todolists.user (username,first_name,last_name,password,instructor) ";
			sql += "VALUES (?,?,?,?,?);";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, user.getUsername());
			myStmt.setString(2, user.getFirst_Name());
			myStmt.setString(3, user.getLast_Name());			
			myStmt.setString(4, user.getPassword());
			myStmt.setBoolean(5, user.isInstructor());
			myStmt.execute();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		close(myConn,myStmt,null);
		
	}
	
	
	// Permet d'ajouter une tâche dans la base de données
	// Utilisé dans AddToDo
	// Prend en parametre la tâche à ajouter et la liste des étudiants concernés par cette tâche
	public void AddTodo(Todo todo, List<Integer> list_user) throws Exception{
        Connection myConn=null; //Pour créer la connexion avec la database
        PreparedStatement myStmt = null; //Pour envoyer la requête sql
        try {
            myConn = dataSource.getConnection();
            String sql= "INSERT INTO todolists.todos (description,id_creator, id_user) ";
            sql += "VALUES (?,?,?);";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, todo.getDescription());
            myStmt.setInt(2, todo.getCreator_id());
            //On remplace le id_user (cad la personne à qui la tâche est affiliée)
            //et on réexécute le statement à chaque fois
            for(int i=0; i<list_user.size();i++) {
                myStmt.setInt(3, list_user.get(i));
                myStmt.execute();
            }
           
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close(myConn,myStmt,null);
    }
	
	// Permet de récuperer la liste de tous les élèves de la BDD
	// Utilisé dans AddTodo pour afficher au professeur la liste de tous les étudiants
	// Ne prend pas de parametre et retourne une liste de User, qui est une liste d'élèves
	public List<User> getStudents() throws Exception {
		Connection myConn=null; //Pour créer la connexion avec la database
		Statement myStmt = null; //Pour envoyer la requête sql
		ResultSet myRs= null; //Pour récupérer les données renvoyées par sql
		List<User> users = new ArrayList<User>();
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from todolists.user where instructor= false;";
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()){
				int id=myRs.getInt("id_user");
				String firstname=myRs.getString("first_name");
				String lastname=myRs.getString("last_name");
				User user= new User(id,firstname, lastname);
				users.add(user);
			}	
			return users;
		} 
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	
	// Permet de modifier une tâche dans la base de données
	// Utilisé dans Edit 
	// Prend en paramètre la tâche à modifier et sa nouvelle description
	public void Edit(Todo todo, String new_description) throws Exception{
        Connection myConn=null; //Pour créer la connexion avec la database
        PreparedStatement myStmt = null; //Pour envoyer la requête sql
        ResultSet myRslt = null;
        try {
            myConn = dataSource.getConnection();
            String sql= "UPDATE todos SET description=? WHERE id_creator=? and description=?;";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1,new_description);
            myStmt.setInt(2, todo.getCreator_id());
            myStmt.setString(3, todo.getDescription());
            myStmt.execute();
        }
        finally{
            close(myConn,myStmt,myRslt);
        }
    }
	
    // Permet de supprimer une tâche dans la base de données
	// Utilisé dans Delete
	// Prend en paramètre la tâche qui doit être supprimé
    public void Delete(Todo todo) throws Exception{
        Connection myConn=null; //Pour créer la connexion avec la database
        PreparedStatement myStmt = null; //Pour envoyer la requête sql
        ResultSet myRslt = null;
        try {
            myConn = dataSource.getConnection();
            String sql= "DELETE FROM todos WHERE id_creator=? and description=?;";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1,todo.getCreator_id());
            myStmt.setNString(2, todo.getDescription());
            myStmt.execute();
        }
        finally{
            close(myConn,myStmt,myRslt);
        }
    }
	
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try{
			if(myStmt!=null)
			myStmt.close();
			if(myRs!=null)
			myRs.close();
			if(myConn!=null)
			myConn.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
}
