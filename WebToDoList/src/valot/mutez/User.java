package valot.mutez;

public class User {
	private int id;
	private String first_Name;
	private String last_Name;
	private String username;
	private String password;
	private boolean instructor;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getFirst_Name() {	return first_Name;}
	public void setFirst_Name(String first_Name) {this.first_Name = first_Name;}
	
	public String getLast_Name() {return last_Name;	}
	public void setLast_Name(String last_Name) {this.last_Name = last_Name;	}
	
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;	}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;	}
	
	public boolean isInstructor() {return instructor;}
	public void setInstructor(boolean instructor) {this.instructor = instructor;}
	
	public User(int id, String first_Name, String last_Name, String username, String password, boolean instructor) {
		super();
		this.id = id;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.username = username;
		this.password=password;
		this.instructor=instructor;
	}
	
	//On surcharge une première fois le constructeur pour pouvoir créer un user sans avoir
	//son id ce qui est utile pour créer un user au sein de l'application sans l'avoir ajouté à la DB 
	//car l'id de ce dernier est crée au moment de la création dans la DB)
	public User(String first_Name, String last_Name, String username, String password, boolean instructor) {
		super();//lier à la classe dont c'est dérivé
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.username = username;
		this.password=password;
		this.instructor=instructor;
	}
	
	//On surcharge le constructeur une nouvelle fois pour créer un user avec pour seuls
	//atributs son id, son nom et son prénom pour la méthode qui récupère la liste des étudiants.
	public User(int id, String first_Name, String last_Name) {
		super();//lier à la classe dont c'est dérivé
		this.id=id;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
	}
}
