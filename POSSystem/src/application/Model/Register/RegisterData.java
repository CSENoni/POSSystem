package application.Model.Register;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

public class RegisterData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String registerId;
	private int userId;
	private Date date;
	
	public RegisterData(int userId) {
		this.registerId = getUniqueId();
		this.userId = userId;
		this.date = new Date();
	}
	
	public String getRegisterId() {
		return registerId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
 			e.printStackTrace();
 		}
 		return null;
 	}
}
