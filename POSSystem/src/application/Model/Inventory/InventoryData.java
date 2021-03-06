package application.Model.Inventory;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long productId;
	private String productName;
	private String supplier;
	private double price;
	private int stockQuantity; //the number of items in stock at the store
	private int outstandingOrder = 0; // number of quantity in pending orders
	private int threshold;
	private static AtomicInteger id_generator = new AtomicInteger(1); 
	private int saleQuantity; //number of items in a sale
	private double saleTotal; //total cost of item price x quantity
	private DecimalFormat decim = new DecimalFormat("#,##0.00");
	private int returnQuantity;
	private double returnTotal;

	
	public InventoryData(String productName, String supplier, double price, int quantity, int threshold) {
		this.productId = genProductID();
		this.productName = productName;
		this.price = price;
		this.supplier = supplier;
		this.stockQuantity = quantity;
		this.threshold = threshold;
	}
	
	public Long getProductId() {
		return productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String name) {
		this.productName = name;
	}
	
	public String getSupplier() {
		return this.supplier;
	}
	
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getPrintPrice() {
		return decim.format(this.price);
	}
	
	public int getStockQuantity() {
		return stockQuantity;
	}
	
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	public int getOutstandingOrder() {
		return outstandingOrder;
	}
	
	public void setOutstandingOrder(int quantity) {
		this.outstandingOrder = quantity;
	}
	
	public int getThreshold() {
		return this.threshold;
	}
	
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	
	public void setSaleQuantity(int number) {
		this.saleQuantity = number;
		this.saleTotal = number * this.price;
	}
	public int getSaleQuantity() {
		return this.saleQuantity;
	}
	
	public double getSaleTotal() {
		return this.saleTotal;
	}
	
	public String getPrintSaleTotal() {
		return decim.format(this.saleTotal);
	}
	
	public boolean isOnSale() {
		return this.saleQuantity > 0;
	}
	
	public void setReturnQuantity(int number) {
		this.returnQuantity = number;
		this.returnTotal = this.getPrice() * number;
	}
	
	public int getReturnQuantity() {
		return this.returnQuantity;
	}
	
	public String getPrintReturnTotal() {
		return decim.format(this.returnTotal);
	}
	
	public long genProductID() {
		Date time = new Date();
		SimpleDateFormat genID = new SimpleDateFormat("yyMMddssMs");
		String saleID = genID.format(time);
		return Long.parseLong(saleID);
	}
	
}
