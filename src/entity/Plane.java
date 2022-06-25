package entity;

import java.sql.Timestamp;

public class Plane {
	String trainno;
	int traintypeno;
	String trainstart;
	String trainend;
	Timestamp timestart;
	Timestamp timereach;
	String timemove;
	
	public String getTrainno() {
		return trainno;
	}

	public void setTrainno(String trainno) {
		this.trainno = trainno;
	}
	
	public int getTraintypeno() {
		return traintypeno;
	}
 
	public void setTraintypeno(int traintypeno) {
		this.traintypeno = traintypeno;
	}

	public String getTrainstart() {
		return trainstart;
	}
	
	public void setTrainstart(String trainstart) {
		this.trainstart=trainstart;
	}
	
	public String getTrainend() {
		return trainend;
	}
	
	public void setTrainend(String trainend) {
		this.trainend=trainend;
	}
	
	public Timestamp getTimestart() {
		return timestart;
	}
	
	public void setTimestart(Timestamp timestart) {
		this.timestart=timestart;
	}
	
	public Timestamp getTimereach() {
		return timereach;
	}
	
	public void setTimereach(Timestamp timereach) {
		this.timereach=timereach;
	}
	
	public String getTimemove() {
		return timemove;
	}

	public void setTimemove(String timemove) {
		this.timemove = timemove;
	}
	@Override
	public String toString() {
		return "Train [trainno=" + trainno + ", traintypeno=" + traintypeno + ", trainstart=" + trainstart + ", trainend=" + trainend + ", timestart=" + timestart + ", timereach=" + timereach + ", timemove=" + timemove + "]";
	}

}
