package types;

import java.sql.Timestamp;

public class TrainAndTicketType {
    public String trainno = "";
    public int traintypeno = 0;
    public String trainstart = "";
    public String trainend = "";
    public String timestart = "";
    public String timereach = "";
    public String timemove = "";
    public float discount = 1;
    public int tickethigh = 0;
    public int ticketone = 0;
    public int integration = 0;
    public int tickettwo = 0;
    public int miles = 0;

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    public int getTickethigh() {
        return tickethigh;
    }

    public void setTickethigh(int tickethigh) {
        this.tickethigh = tickethigh;
    }

    public int getTicketone() {
        return ticketone;
    }

    public void setTicketone(int ticketone) {
        this.ticketone = ticketone;
    }

    public int getTickettwo() {
        return tickettwo;
    }

    public void setTickettwo(int tickettwo) {
        this.tickettwo = tickettwo;
    }


    public TrainAndTicketType(String trainno, int traintypeno, String trainstart, String trainend, Timestamp timestart,
                              Timestamp timereach, String timemove, float discount,int miles, int tickethigh, int ticketone, int tickettwo) {
        this.trainno = trainno;
        this.traintypeno = traintypeno;
        this.trainstart = trainstart;
        this.trainend = trainend;
        this.timestart = timestart.toString();
        this.timereach = timereach.toString();
        this.timemove = timemove;
        this.discount = discount;
        this.miles = miles;
        this.tickethigh = tickethigh;
        this.ticketone = ticketone;
        this.tickettwo = tickettwo;
    }

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
        this.trainstart = trainstart;
    }

    public String getTrainend() {
        return trainend;
    }

    public void setTrainend(String trainend) {
        this.trainend = trainend;
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

    public String getTimemove() {
        return timemove;
    }

    public void setTimemove(String timemove) {
        this.timemove = timemove;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "TrainAndTicketType{" +
                "trainno:'" + trainno + '\'' +
                ", traintypeno:" + traintypeno +
                ", trainstart:'" + trainstart + '\'' +
                ", trainend:'" + trainend + '\'' +
                ", timestart:'" + timestart + '\'' +
                ", timereach:'" + timereach + '\'' +
                ", timemove:'" + timemove + '\'' +
                ", discount:" + discount + '\'' +
                ", miles:" + miles + '\'' +
                ", tickethigh:" + tickethigh + '\'' +
                ", ticketone:" + ticketone + '\'' +
                ", tickettwo:" + tickettwo + '\'' +
                ", integration:" + integration + '\'' +

                '}';
    }
}
