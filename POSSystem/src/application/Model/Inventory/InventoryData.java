package application.Model.Inventory;

public class InventoryData {
	private int productId;
	private String productName;
	private int price;
	private int quantity;
	private List<Integer> productIds;
	
	public InventoryData(int productId, String productName, int price, int quantity) {
		this.proudctId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	
	public getProductId() {
		return productId;
	}
	
	public getProductName() {
		return productName;
	}
	
	public getPrice() {
		return price;
	}
	
	public getQuantity() {
		return quantity;
	}
	
	public void addProduct() {
		this.quantity++;
	}
	
	public void removeProduct() {
		this.quantity--;
	}
}
