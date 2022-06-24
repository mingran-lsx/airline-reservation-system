package entity;

public class User {
	String usertel;
	String userpwd;
	String username;
	int idtypeno;
	String id;
	int usertypeno;
	
	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}
	
	public String getUserpwd() {
		return userpwd;
	}
 
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdtypeno() {
		return idtypeno;
	}

	public void setIdtypeno(int idtypeno) {
		this.idtypeno = idtypeno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUsertypeno() {
		return usertypeno;
	}

	public void setUsertypeno(int usertypeno) {
		this.usertypeno = usertypeno;
	}
	@Override
	public String toString() {
		return "User [usertel=" + usertel + ", userpwd=" + userpwd + ", username=" + username + ", idtypeno=" + idtypeno
				+ ", id=" + id + ", usertypeno=" + usertypeno + "]";
	}
}