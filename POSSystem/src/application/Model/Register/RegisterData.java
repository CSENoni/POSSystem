package application.Model.Register;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RegisterData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private List<Integer> userIds;
	private Date date;
	private double amountOfSales;
	private static AtomicInteger id_intgenerator = new AtomicInteger(0);
	
	public RegisterData(int userId, Date date, double amountOfSales) {
		String id_generator = getUniqueId();
		if(id_generator == null) {
			id = "" + id_intgenerator.getAndIncrement();
		}else {
			id = id_generator;
		}
		userIds = new LinkedList<Integer>();
		userIds.add(userId);
		this.date = date;
		this.amountOfSales = amountOfSales;
	}
	
	public String getId() {
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
	
	private static String getUniqueId() {
 		try {
 			InetAddress address = InetAddress.getLocalHost();
 		    NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);
 		    byte[] macAddress = networkInterface.getHardwareAddress();
 		    StringBuilder sb = new StringBuilder();
 		   for (int byteIndex = 0; byteIndex < macAddress.length; byteIndex++) {
 			    sb.append(String.format("%02X%s", macAddress[byteIndex], ""));
 			}
 			return sb.toString();
 		} catch (SocketException | UnknownHostException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		return null;
 	}
	
	@Override
	public String toString() {
		return "Register: " + this.id + " Amount: " + this.amountOfSales;
	}
}
