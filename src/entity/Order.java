package entity;

import java.sql.Timestamp;

public class Order {
	String usertel;
	String pertel;
	String pername;
	String perid;
	String trainno;
	String trainstart;
	String stationinfo;
	String timestart;
	String timereach;
	String seattrain;
	int seatno;
	String seattype;
	int price;
	Timestamp buydate;

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setPertel(String pertel) {
		this.pertel = pertel;
	}

	public String getPertel() {
		return pertel;
	}

	public void setPername(String pername) {
		this.pername = pername;
	}

	public String getPername() {
		return pername;
	}

	public void setPerid(String perid) {
		this.perid = perid;
	}

	public String getPerid() {
		return perid;
	}

	public void setTrainno(String trainno) {
		this.trainno = trainno;
	}

	public String getTrainno() {
		return trainno;
	}

	public void setTrainstart(String trainstart) {
		this.trainstart = trainstart;
	}

	public String getTrainstart() {
		return trainstart;
	}

	public void setStationinfo(String stationinfo) {
		this.stationinfo = stationinfo;
	}

	public String getStationinfo() {
		return stationinfo;
	}

	public String getTimestart() {
		return timestart;
	}

	public void setTimestart(String timestart) {
		this.timestart = timestart;
	}

	public String getTimereach() {
		return timereach;
	}

	public void setTimereach(String timereach) {
		this.timereach = timereach;
	}

	public String getSeattrain() {
		return seattrain;
	}

	public void setSeattrain(String seattrain) {
		this.seattrain = seattrain;
	}

	public int getSeatno() {
		return seatno;
	}

	public void setSeatno(int seatno) {
		this.seatno = seatno;
	}

	public String getSeattype() {
		return seattype;
	}

	public void setSeattype(String seattype) {
		this.seattype = seattype;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Timestamp getBuydate() {
		return buydate;
	}

	public void setBuydate(Timestamp buydate) {
		this.buydate = buydate;
	}

	@Override
	public String toString() {
		return "Order [usertel=" + usertel + ", pertel=" + pertel + ", pername=" + pername + ", perid=" + perid
				+ ", trainno=" + trainno + ", trainstart=" + trainstart + ", stationinfo=" + stationinfo
				+ ", timestart=" + timestart + ", timereach=" + timereach + ", seattrain=" + seattrain + ", seatno="
				+ seatno + ", seattype=" + seattype + ", price=" + price + ", buydate=" + buydate + "]";
	}
}
