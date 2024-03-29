package Shop2;

public class ProductStock {
	private Product product;
	private int quantity;
	
	public ProductStock(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return String.format("%s x %3d\n",product, quantity);
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
