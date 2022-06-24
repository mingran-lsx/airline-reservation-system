package entity;

public class Userperson {
	String usertel;
	String pertel;
	String pername;
	int idtypeno;
	String perid;
	int pertypeno;
	
	public String getUsertel() {
		return usertel;
	}
 
	public void setUsertel(String usertel) {
		this.usertel=usertel;
	}
	
	public String getPertel() {
		return pertel;
	}

	public void setPertel(String pertel) {
		this.pertel = pertel;
	}

	public String getPername() {
		return pername;
	}

	public void setPername(String pername) {
		this.pername = pername;
	}

	public int getIdtypeno() {
		return idtypeno;
	}

	public void setIdtypeno(int idtypeno) {
		this.idtypeno = idtypeno;
	}

	public String getPerid() {
		return perid;
	}

	public void setPerid(String perid) {
		this.perid = perid;
	}

	public int getPertypeno() {
		return pertypeno;
	}

	public void setPertypeno(int pertypeno) {
		this.pertypeno = pertypeno;
	}
	@Override
	public String toString() {
		return "Userperson [usertel=" + usertel + ", pertel=" + pertel + ", pername=" + pername + ", idtypeno=" + idtypeno
				+ ", perid=" + perid + ", pertypeno=" + pertypeno + "]";
	}


}
