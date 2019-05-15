package application.Model.Sale;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import application.Model.Inventory.InventoryData;
import application.Model.Register.RegisterData;
import application.Model.Register.RegisterUtils;
import javafx.collections.ObservableList;

public class SaleData implements Serializable {

	private static final long serialVersionUID = 1L;
	private long saleNumber;
	private String saleTime;
	private double saleTotal = 0.0;
	private int userId;
	private String registerId;
	private ArrayList<InventoryData> items = new ArrayList<InventoryData>();
	DecimalFormat decim = new DecimalFormat("#,##0.00");
	private int numItems; // Total number of items in a sale
	private double paid;
	private double change;
	private String type;

	public SaleData() {
		this.saleNumber = genSaleID();
		DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm");
		Date date = new Date();
		this.saleTime = dateFormat.format(date);
		RegisterData reg = RegisterUtils.getCurrentRegisterData();
		if(reg != null) {
			registerId = reg.getRegisterId();
			userId = reg.getUserId();
		}
		this.type = "Sale";
	}

	// Getters
	public long getSaleNumber() {
		return this.saleNumber;
	}

	public String getSaleTime() {
		return this.saleTime;
	}

	public void setSaleTotal(double number) {
		this.saleTotal = number;
	}
	
	public double getSaleTotal() {
		return this.saleTotal;
	}

	public int getUserId() {
		return this.userId;
	}

	public String getRegisterId() {
		return this.registerId;
	}

	public String getPrintSaleTotal() {
		return "$" + decim.format(this.saleTotal);
	}

	public void setPaid(double payment) {
		this.paid = payment;
	}

	public double getPaid() {
		return this.paid;
	}

	public String getPrintPaid() {
		return "$" + decim.format(this.paid);
	}

	public void setChange(double amount) {
		this.change = amount;
	}

	public double getChange() {
		return this.change;
	}

	public String getPrintChange() {
		return "$" + decim.format(this.change);
	}

	// get the number of items in a sale
	public int getNumItems() {
		return this.numItems;
	}
	
	public ArrayList<InventoryData> getSaleItems(){
		return this.items;
	}
	
	//Adding and Removing items from a sale
	public void editSaleItems(ObservableList<InventoryData> products) {
		if (!products.isEmpty()) {
			ObservableList<InventoryData> itemList = products;
			items.clear();
			items.addAll(itemList);
			this.numItems = 0;
			this.saleTotal = 0.00;
			for (InventoryData product : products) {
				this.saleTotal = this.saleTotal + product.getSaleTotal();
				this.numItems = this.numItems + product.getSaleQuantity();
			}
		}
	}

	// Generate unique sale id
	public long genSaleID() {
		Date time = new Date();
		SimpleDateFormat genID = new SimpleDateFormat("yyMMddhhmmssMs");
		String saleID = genID.format(time);
		return Long.parseLong(saleID);
	}

	public void setSaleTime() {
		DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm");
		Date date = new Date();
		this.saleTime = dateFormat.format(date);
	}
	
	public void setType(String newType) {
		this.type = newType;
	}
	
	public String getType() {
		return this.type;
	}
	
}
