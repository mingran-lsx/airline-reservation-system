package entity;

import java.sql.Timestamp;

public class Station {
	String trainno;
	String stationno;
	String stationinfo;
	Timestamp timereach;
	Timestamp timestart;
	String stationmile;
	
	public String getTrainno() {
		return trainno;
	}

	public void setTrainno(String trainno) {
		this.trainno = trainno;
	}
	
	public String getStationno() {
		return stationno;
	}
 
	public void setStationno(String stationno) {
		this.stationno = stationno;
	}

	public String getStationinfo() {
		return stationinfo;
	}
	
	public void setStationinfo(String stationinfo) {
		this.stationinfo=stationinfo;
	}
	
	public Timestamp getTimereach() {
		return timereach;
	}
	
	public void setTimereach(Timestamp timereach) {
		this.timereach=timereach;
	}
	
	public Timestamp getTimestart() {
		return timestart;
	}
	
	public void setTimestart(Timestamp timestart) {
		this.timestart=timestart;
	}
	
	public String getStationmile() {
		return stationmile;
	}

	public void setStationmile(String stationmile) {
		this.stationmile = stationmile;
	}
	@Override
	public String toString() {
		return "Station [trainno=" + trainno + ", stationno=" + stationno + ", stationinfo=" + stationinfo + ", timereach=" + timereach + ", timestart=" + timestart + ", stationmile=" + stationmile + "]";
	}

}
