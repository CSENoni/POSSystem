package application.Model.Sale;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	private String allSaleItems;
	private String output;

	
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
	
	//Print Items of Sale 
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
		String lines = String.format("%-20s %5s %11s\n", "-------", "--------", "------");
		this.allSaleItems = top + lines;
		
		for (Map.Entry<InventoryData, Integer> entry : frequencyMap.entrySet()) {
			String sale = String.format("%-20s %4s %15s\n",entry.getKey().getProductName(), entry.getValue(), decim.format(entry.getKey().getPrice() * entry.getValue()));
			this.allSaleItems = this.allSaleItems + sale;
		}
		System.out.print(allSaleItems);
	}
	
	//Checkout Method
	public void checkout(double payment) {
		double changeCalc = payment - Double.parseDouble(this.printSaleTotal());
		
		try {
			File file = new File(new File("").getAbsoluteFile() + File.separator + "allSales.txt");
			FileWriter fileWriter = new FileWriter(file, true);
			output = this.allSaleItems;
			output = ("Sale Number: " + this.getSaleNumber() + "\n" + this.getSaleTime() + "\n\n" + this.output);
			String total = String.format("\n%41s", "Total: $" + this.printSaleTotal());
			String pay = String.format("\n%41s", "Payment: $" + decim.format(payment));
			String change = String.format("\n%42s", "Change: $" + decim.format(changeCalc) + "\n\n");
			output = this.output + total + pay + change;
			output = this.output + "---------------------------------------------\n\n";
			
			if (!file.exists()) {
				file.createNewFile();
				fileWriter.write(output);
				fileWriter.close();
			}
			else {
				fileWriter.write(output);
				fileWriter.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
		
}

