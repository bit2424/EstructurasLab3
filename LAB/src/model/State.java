package model;
import java.io.Serializable;
import java.util.Date;

public class State implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private Date date;
	private double value;
	public State(String data, double value) {
		this.date = convertDate(data);
		this.value = value;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	private Date convertDate(String data) {
		String q[]=data.split(" ");
		int year = Integer.parseInt(q[1].split("/")[2]);
		int month = Integer.parseInt(q[1].split("/")[1]);
		int date = Integer.parseInt(q[1].split("/")[0]);
		int hrs = Integer.parseInt(q[2].split(":")[0]);
		int min = Integer.parseInt(q[2].split(":")[1]);
		Date dates = new Date(year, month, date, hrs, min);
		return dates;
	}

	@Override
	public String toString() {
		return "State [date=" + date + ", value=" + value + "]";
	}
	

}
