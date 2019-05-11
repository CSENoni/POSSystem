package application.Model.Inventory;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int productId;
	private String productName;
	private String supplier;
	private double price;
	private int stockQuantity; //the number of items in stock at the store
	private int outstandingOrder; // number of quantity in pending orders
	private int threshold;
	private static AtomicInteger id_generator = new AtomicInteger(1); 
	
	public InventoryData(String productName, String supplier, double price, int quantity, int threshold) {
		this.productId = id_generator.getAndIncrement();
		this.productName = productName;
		this.price = price;
		this.supplier = supplier;
		this.stockQuantity = quantity;
		this.threshold = threshold;
	}
	
	public int getProductId() {
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
	
	public int getStockQuantity() {
		return stockQuantity;
	}
	
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	public int getOutstandingOrder() {
		return outstandingOrder;
	}
	
	public void addOutstandingOrder(int quantity) {
		this.outstandingOrder += quantity;
	}
	
	public void removeOutstandingOrder(int quantity) {
		this.outstandingOrder -= quantity;
	}
	
	public void addProduct(int quantity) { //after replenishing the inventory or a return
		this.stockQuantity += quantity;
	}
	
	public void removeProduct(int quantity) { //after a customer makes a purchase
		this.stockQuantity -= quantity;
	}
	
	public int getThreshold() {
		return this.threshold;
	}
	
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
}
