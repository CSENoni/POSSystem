package application.Model.Register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RegisterUtils {
	public static void write(RegisterData data) {
		try {
			List<RegisterData> datas = getAll();
			
			FileOutputStream fs = new FileOutputStream(new File("Register.txt"));
			ObjectOutputStream o = new ObjectOutputStream(fs);
			
			if(datas == null) {
				List<RegisterData> d = new ArrayList<RegisterData>();
				d.add(data);
				o.writeObject(d);
			}else {
				datas.add(data);
				o.writeObject(datas);
			}
			
			o.close();
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<RegisterData> getAll() {
		try {
			File file = new File("example.txt");
			FileInputStream fs = null;
			ObjectInputStream oi = null;
			if(file.exists()) {
				fs = new FileInputStream(file);
			    oi = new ObjectInputStream(fs);
			    List<RegisterData> datas = (List<RegisterData>) oi.readObject();
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
