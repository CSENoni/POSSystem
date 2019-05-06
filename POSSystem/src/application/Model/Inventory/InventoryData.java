package application.Model.Inventory;

public class InventoryData {
	private int productId;
	private String productName;
	private double price;
	private int stockQuantity; //the number of items in stock at the store
	
	public InventoryData(int productId, String productName, double price, int quantity) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getStockQuantity() {
		return stockQuantity;
	}
	
	public void addProduct() { //after replenishing the inventory or a return
		this.stockQuantity++;
	}
	
	public void removeProduct() { //after a customer makes a purchase
		this.stockQuantity--;
	}
}
