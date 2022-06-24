package entity;

public class Manager {
	String managerno;
	String managerpwd;
	
	public String getManagerno() {
		return managerno;
	}

	public void setManagerno(String managerno) {
		this.managerno = managerno;
	}
	
	public String getManagerpwd() {
		return managerpwd;
	}
 
	public void setManagerpwd(String managerpwd) {
		this.managerpwd = managerpwd;
	}
	@Override
	public String toString() {
		return "Manager [managerno=" + managerno + ", managerpwd=" + managerpwd + "]";
	}
}
