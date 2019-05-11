package application.Model.Inventory;

import java.io.File;
import java.io.FileInputStream;
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
	
	public static void remove(InventoryData data) {
		List<InventoryData> datas = getAll();
		
		if(datas != null) {
			for(InventoryData item : datas) {
				if(item.getProductId() == data.getProductId() && item.getProductName().equals(data.getProductName()) 
						&& item.getSupplier().equals(data.getSupplier())) {
					datas.remove(item);
					break;
				}
			}
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
	
	public static void update(int pos, InventoryData data) {
		List<InventoryData> datas = getAll();
		
		if(datas != null) {
			datas.get(pos).setProductName(data.getProductName());
			datas.get(pos).setSupplier(data.getSupplier());
			datas.get(pos).setPrice(data.getPrice());
			datas.get(pos).setStockQuantity(data.getStockQuantity());
			datas.get(pos).setThreshold(data.getThreshold());
			
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
