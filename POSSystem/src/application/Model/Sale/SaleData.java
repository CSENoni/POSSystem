package application.Model.Sale;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import application.Model.Inventory.InventoryData;
import application.Model.Register.RegisterData;
import application.Model.User.UserData;
import javafx.collections.ObservableList;

public class SaleData implements Serializable{

	private static final long serialVersionUID = 1L;
	private int saleNumber;
	private static AtomicInteger saleGen = new AtomicInteger(1);
	private String saleTime;
	private double saleTotal = 0.0;
	private UserData user;
	private RegisterData register;
	private ArrayList<InventoryData> items = new ArrayList<InventoryData>();
	DecimalFormat decim = new DecimalFormat("#,##0.00");
	private int numItems;	//Total number of items in a sale
	
	
	public SaleData() {
		this.saleNumber = saleGen.getAndIncrement();
		DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		Date date = new Date();
		this.saleTime = dateFormat.format(date);
	}
	
	//Getters
	public int getSaleNumber() {
		return this.saleNumber;
	}
	public String getSaleTime() {
		return this.saleTime;
	}
	public double getSaleTotal() {
		return this.saleTotal;
	}
	public int getUser() {
		return this.user.getId();
	}
	public String getRegister() {
		return this.register.getId();
	}
	
	public String getPrintSaleTotal() {
		return decim.format(this.saleTotal);
	}
	
	//get the number of items in a sale
	public int getNumItems() {
		return this.numItems;
	}
	
	//Adding and Removing items from a sale
	public void editSaleItems(ObservableList<InventoryData> products) {
		if (!products.isEmpty()) {
			ObservableList<InventoryData> itemList = products;
			items.addAll(itemList);
			this.numItems = 0;
			this.saleTotal = 0.00;
			for (InventoryData product : products) {
				this.saleTotal = this.saleTotal + product.getSaleTotal();
				this.numItems = this.numItems + product.getSaleQuantity();
			}
		}
	}
	
	
	//Cancel a Sale
	public void cancelSale() {
		Iterator<InventoryData> iterator = items.iterator(); 
		while (iterator.hasNext()) {
			InventoryData product = iterator.next();
			if (product != null) {
				this.saleTotal = (this.saleTotal - product.getPrice());
				product.setStockQuantity(product.getStockQuantity() + 1);
				iterator.remove();
			}
		}
	}
	

		
}

