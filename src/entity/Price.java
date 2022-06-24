package entity;

public class Price {
	String stationno;
	int pricehigh;
	int priceone;
	int pricetwo;
	
	public void setStationno(String stationno) {
		this.stationno=stationno;
	}
	
	public String getStationno() {
		return stationno;
	}
	
	public void setPricehigh(int pricehigh) {
		this.pricehigh=pricehigh;
	}
	
	public int getPricehigh() {
		return pricehigh;
	}
	
	public void setPriceone(int priceone) {
		this.priceone=priceone;
	}
	
	public int getPriceone() {
		return priceone;
	}
	
	public void setPricetwo(int pricetwo) {
		this.pricetwo=pricetwo;
	}
	
	public int getPricetwo() {
		return pricetwo;
	}
	@Override
	public String toString() {
		return "Price [stationno=" + stationno + ", pricehigh=" + pricehigh + ", priceone=" + priceone + ", pricetwo=" + pricetwo + "]";
	}
}
