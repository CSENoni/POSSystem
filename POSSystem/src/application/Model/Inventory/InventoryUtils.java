package application.Model.Inventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InventoryUtils {
	public static void write(InventoryData data) {
		try {
			List<InventoryData> datas = getAll();

			FileOutputStream fs = new FileOutputStream(new File(new File("").getAbsoluteFile() + File.separator + "Inventory.txt"));
			ObjectOutputStream o = new ObjectOutputStream(fs);

			if (datas == null) {
				List<InventoryData> d = new ArrayList<InventoryData>();
				d.add(data);
				o.writeObject(d);
			} else {
				datas.add(data);
				o.writeObject(datas);
			}

			o.close();
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<InventoryData> getAll() {
		try {
			File file = new File(new File("").getAbsoluteFile() + File.separator + "Inventory.txt");
			FileInputStream fs = null;
			ObjectInputStream oi = null;
			if (file.exists()) {
				fs = new FileInputStream(file);
				oi = new ObjectInputStream(fs);
				List<InventoryData> datas = (List<InventoryData>) oi.readObject();
				oi.close();
				fs.close();
				return datas;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void remove(int position) {
		List<InventoryData> datas = getAll();
		
		if(datas != null) {
			datas.remove(position);
			try {
				FileOutputStream fs = new FileOutputStream(new File(new File("").getAbsoluteFile() + File.separator + "Inventory.txt"));
				ObjectOutputStream o = new ObjectOutputStream(fs);
				
				o.writeObject(datas);
				o.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
