package application.Model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserUtils {
	public static void write(UserData user) {
		List<UserData> datas = getAll();
		
		try {
			FileOutputStream fs = new FileOutputStream(new File(new File("").getAbsoluteFile() + File.separator + "User.txt"));
			ObjectOutputStream o = new ObjectOutputStream(fs);
			
			if(datas == null) {
				List<UserData> d = new ArrayList<UserData>();
				d.add(user);
				o.writeObject(d);
			}else {
				datas.add(user);
				o.writeObject(datas);
			}
			
			o.close();
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UserData get(String username, String password) {
		File file = new File(new File("").getAbsoluteFile() + File.separator + "User.txt");
		FileInputStream fs = null;
		ObjectInputStream o = null;
		if(file.exists()) {
			try {
				fs = new FileInputStream(file);
				o = new ObjectInputStream(fs);
				
				List<UserData> datas = (List<UserData>) o.readObject();
				o.close();
				fs.close();
				
				for(UserData data : datas) {
					if(data.getUserName().equals(username) && data.getPassWord().equals(password)) {
						return data;
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private static List<UserData> getAll() {
		try {
			File file = new File(new File("").getAbsoluteFile() + File.separator + "User.txt");
			FileInputStream fs = null;
			ObjectInputStream oi = null;
			if(file.exists()) {
				fs = new FileInputStream(file);
			    oi = new ObjectInputStream(fs);
			    List<UserData> datas = (List<UserData>) oi.readObject();
			    oi.close();
				fs.close();
				return datas;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
