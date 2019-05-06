package application.Model.User;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class UserData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String userName;
	private String passWord;
	private static AtomicInteger id_generator = new AtomicInteger(0);
	
	public UserData(String userName, String passWord) {
		this.id = id_generator.getAndIncrement();
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getPassWord() {
		return this.passWord;
	}
}
