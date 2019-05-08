package application.Model.Sale;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import application.Model.Inventory.InventoryData;
import application.Model.Register.RegisterData;
import application.Model.User.UserData;

public class SaleData {

	private int saleNumber;
	private static AtomicInteger saleGen = new AtomicInteger(1);
	private String saleTime;
	private double saleTotal = 0.0;
	private UserData user;
	private RegisterData register;
	private ArrayList<InventoryData> items;
	
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
	
	//Adding and Removing items from a sale
	public void addSaleItem(InventoryData item) {
		items.add(item);
		this.saleTotal = this.saleTotal + item.getPrice();
	}
	
	public void removeSaleItem(String itemName) {
		int itemIndex;
		for (InventoryData product : items) {
			if (itemName.equalsIgnoreCase(product.getProductName())) {
				itemIndex = items.indexOf(product);
				items.remove(itemIndex);
				this.saleTotal = this.saleTotal - product.getPrice();
			}
		}
	}
	
	//Cancel a Sale
	public void cancelSale() {
		this.items.clear();
		this.saleTotal = 0.0;
	}
	
	//Get the names of all items in sale
	public ArrayList<String> getSaleItemNames(ArrayList<InventoryData> items) {
		ArrayList<String> itemNames = new ArrayList<String>();
		for (InventoryData product : items) {
			itemNames.add(product.getProductName());
		}
		return itemNames;
	}
}

