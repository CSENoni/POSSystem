package application.Model.Sale;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
	private ArrayList<InventoryData> items = new ArrayList<InventoryData>();
	DecimalFormat decim = new DecimalFormat("#,###.00");

	
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
	
	public String printSaleTotal() {
		return decim.format(this.saleTotal);
	}
	
	//Adding and Removing items from a sale
	public void addSaleItem(InventoryData item) {
		items.add(item);
		this.saleTotal = this.saleTotal + item.getPrice();
		item.setStockQuantity(item.getStockQuantity() - 1);
	}
	
	public void removeSaleItem(String itemName) {
		Iterator<InventoryData> iterator = items.iterator(); 
		while (iterator.hasNext()) {
			InventoryData product = iterator.next();
			if (itemName.equalsIgnoreCase(product.getProductName())) {
				this.saleTotal = (this.saleTotal - product.getPrice());
				product.setStockQuantity(product.getStockQuantity() + 1);
				iterator.remove();
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
	
	//Print Sale 
		public void countItems() {
			Map<InventoryData, Integer> frequencyMap = new HashMap<>();
			for (InventoryData s: items) {
				Integer count = frequencyMap.get(s);
				if (count == null) {
					count = 0;
				}
				frequencyMap.put(s, count + 1);
			}
			
			String top = String.format("%-20s %5s %10s\n", "Product", "Quantity", "Price");
			String lines = String.format("%-20s %5s %11s", "-------", "--------", "------");
			String header = top + lines;
			System.out.println(header);
			
			for (Map.Entry<InventoryData, Integer> entry : frequencyMap.entrySet()) {
				String output = String.format("%-20s %4s %15s\n",entry.getKey().getProductName(), entry.getValue(), decim.format(entry.getKey().getPrice() * entry.getValue()));
				System.out.print(output);
			}
		}
		
		
}

