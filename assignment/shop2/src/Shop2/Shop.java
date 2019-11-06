package Shop2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop {

	private double cash;
	private ArrayList<ProductStock> stock;

	public Shop(String fileName) {
		stock = new ArrayList<>();
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			//System.out.println(lines.get(0));
			cash = Double.parseDouble(lines.get(0));
			// i am removing at index 0 as it is the only one treated differently
			lines.remove(0);
			for (String line : lines) {
				//System.out.println(line);
				String[] arr = line.split(",");
				String name = arr[0];
				double price = Double.parseDouble(arr[1]);
				int quantity = Integer.parseInt(arr[2].trim());
				Product p = new Product(name, price);
				ProductStock s = new ProductStock(p, quantity);
				stock.add(s);
			}
		}

		catch (IOException e) {

			// do something
			e.printStackTrace();
		}
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public ArrayList<ProductStock> getStock() {
		return stock;
	}
	
	public void setStock(ArrayList<ProductStock> stock) {
		this.stock = stock;
	}	

	@Override
	public String toString() {
		//return "Shop [cash=" + cash + ", stock=" + stock + "]";
		return String.format("Cash: %.2f\n%s",cash, stock);
	}

	public String getItemDetails(String ItemName) {
		String ret="";
		for (ProductStock productStock : stock) {
			if(productStock.getProduct().getName().contains(ItemName)) {
				String product = productStock.getProduct().getName();
				int quantity = productStock.getQuantity();
				double price = productStock.getProduct().getPrice();
				ret = String.format("%s, %.2f, %d",product, price, quantity);
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		ArrayList<String> receipt = new ArrayList<String>;
		Shop shop = new Shop("src/Shop2/stock.csv");
		System.out.println(shop);	
		Customer cust = new Customer("src/Shop2/customer.csv");
		//System.out.println(cust);
		double custBudget = cust.getBudget();
		String custName = cust.getName();
		ArrayList<ProductStock> list = cust.getShoppingList();
		for (ProductStock productStock : list) {
			//get name and quantity from shopping list
			String listName = productStock.getProduct().getName();
			int listQuantity = productStock.getQuantity();
			//then find the item details is the shop
			String ret = shop.getItemDetails(listName);
			String[] found = ret.split(","); //split the returned strings to values  
			String name = found[0].trim();
			double price = Double.parseDouble(found[1].trim());
			int quantity = Integer.parseInt(found[2].trim());
			System.out.printf("%s %f %d",name,price,quantity);
			if (quantity>listQuantity) { //if the quantity wanted is available assign it
				
			} else {//otherwise assigne the balance of the only
				
			}	
				//update the receipt with the name, quantity and amount
				Double lineTotal = listQuantity * listQuantity;
				receipt.add(String.format("%-15s x %3d @ %.2f = %.2f", listName, listQuantity, price, lineTotal));
				//subtract from the stock
				shop.setStock(quantity-listQuantity);
				//and add to the cash
				double val = shop.getCash();
				val+=lineTotal;
				shop.setCash(val);
			
		}
	}


}
