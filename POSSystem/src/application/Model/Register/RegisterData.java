package application.Model.Register;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RegisterData {
	private int id;
	private static AtomicInteger id_generator  = new AtomicInteger(0);
	private List<Integer> userIds;
	private Date date;
	private double amountOfSales;
	
	public RegisterData(int id, int userId, Date date, double amountOfSales) {
		this.id = id_generator.getAndIncrement();
		userIds.add(userId);
		this.date = date;
		this.amountOfSales = amountOfSales;
	}
	
	public int getId() {
		return id;
	}
	
	public List<Integer> getUserIds() {
		return userIds;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmountOfSales() {
		return amountOfSales;
	}
	public void setAmountOfSales(double amountOfSales) {
		this.amountOfSales = amountOfSales;
	}
}
