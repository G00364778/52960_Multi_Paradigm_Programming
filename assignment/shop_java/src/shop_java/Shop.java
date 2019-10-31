package shop_java;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop {
	private double cash;
	private static ArrayList<ProductStock> stock;

	public Shop(String fileName) {
		stock = new ArrayList<>();
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			// System.out.println(lines.get(0));
			String[] vals = lines.get(0).split(",");
			cash = Double.parseDouble(vals[1]);
			// cash = Double.parseDouble(lines.get(0));
			// i am removing at index 0 as it is the only one treated differently
			lines.remove(0);
			for (String line : lines) {
				// System.out.println(line);
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

	public String findProdInfo(String findme){
		String result="NONE";
		for (int i = 0; i < this.stock.size(); i++) {
			ProductStock stockItem = this.stock.get(i);
			if (stockItem.getProduct().getName().equalsIgnoreCase(findme)){
				result="";
				String pName = stockItem.getProduct().getName();
				int pQuantity = stockItem.getQuantity();
				double pPrice = stockItem.getProduct().getPrice();
				result = "" + pName + "," + pPrice + "," + pQuantity;
				System.out.println(result);
				//return result;
			}			
		}
		return result;
	}

	public ArrayList<ProductStock> getStock() {
		return stock;
	}

	@Override
	public String toString() {
		return "Shop [cash=" + cash + ", stock=" + stock + "]";
	}

	public static void printNice(Shop shop) {
		System.out.println(shop);
	}

	public static void main(String[] args) {
		Shop shop = new Shop("src/shop_java/stock.csv");
	
		shop.findProdInfo("Coke Can");
		//printNice(shop);
		// Customer james = new Customer("src/shop_java/customer.csv");
		// System.out.println(james);
	}
	
}
