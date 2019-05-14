package application.Model.Sale;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SaleUtils {
	
	public static void writeSale(SaleData data) {
		try {
			List<SaleData> datas = getAll();

			FileOutputStream fs = new FileOutputStream(new File(new File("").getAbsoluteFile() + File.separator + "CashierReport.txt"));
			ObjectOutputStream o = new ObjectOutputStream(fs);

			if (datas == null) {
				List<SaleData> d = new ArrayList<SaleData>();
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

	public static List<SaleData> getAll() {
		try {
			File file = new File(new File("").getAbsoluteFile() + File.separator + "CashierReport.txt");
			FileInputStream fs = null;
			ObjectInputStream oi = null;
			if (file.exists()) {
				fs = new FileInputStream(file);
				oi = new ObjectInputStream(fs);
				List<SaleData> datas = (List<SaleData>) oi.readObject();
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
