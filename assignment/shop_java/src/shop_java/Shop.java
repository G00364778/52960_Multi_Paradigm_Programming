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

	public String findProdInfo(String findme, int oQuantity){
		/**
		 * <h2>findProductInfo</h2>
		 * 
		 * inputs: String findme, int Quantity
		 * 
		 * findme - the item name in the shop that should be searched for
		 * Quantity - the quantity that is required on ythe order
		 * 
		 * Return Values:
		 * 
		 * NONE or the matched item including name, quaantity and price as well as a comment if
		 * stock was less than match. 
		 */
		String result="NONE";
		int deficit=0;
		String msg="";// populate if stock is short
		for (int i = 0; i < this.stock.size(); i++) {
			ProductStock stockItem = this.stock.get(i);
			if (stockItem.getProduct().getName().equalsIgnoreCase(findme)){
				result="";
				String pName = stockItem.getProduct().getName();
				int pQuantity = stockItem.getQuantity();
				double pPrice = stockItem.getProduct().getPrice();
				System.out.println(result);
				if (oQuantity>pQuantity) {//more things are ordered than available
					deficit = oQuantity - pQuantity;
					oQuantity=pQuantity; //then set the order quantity to the available quantity
				} 
				if (deficit>0) {
					msg="," + deficit + " short";
				}
				double total = oQuantity * pPrice;
				stockItem.setQuantity(pQuantity-oQuantity);//subtract the order quantity from the stock
				result = "" + pName + "," + oQuantity + "," + String.format("%.2f",pPrice) + "," + String.format("%.2f", total) + msg;
				//return result;
			}			
		}
		return result;
	}

	public static void printItemDetails(String sItem, boolean detail) {
		/**
		 * <h2>printItemDetails</h2>
		 * 
		 * Inputs: The item details returned by findProdInfo and a boolean flag for details.
		 * 
		 * When the boolean is true the function outputs a list with labels and values otherwise it 
		 * prints single line with sum and total.
		 *  
		 */
		String[] itenNames = { "Item Name", "Order Quantity", "Unit Price", "Total", "Remark" };
		String[] items = sItem.split(",");
		if (detail==true) {
			for (int i = 0; i < items.length; i++) {
				System.out.println(String.format("%14s: ",itenNames[i]) + items[i]);
			}	
		}
		else{
			if (items.length<=4) {
				System.out.println(String.format("%-10s",items[0]) +" x "+ items[1] +" @ "+ items[2] +" = "+ items[3]);	
			}
			else{
				System.out.println(String.format("%-10s",items[0]) +" x "+ items[1] +" @ "+ items[2] +" = "+ items[3]+" - "+ items[4]);
			}
		}
	}

	public ArrayList<ProductStock> getStock() {
		return stock;
	}

	@Override
	public String toString() {
		return "Shop [cash=" + cash + ", stock=" + stock + "]";
	}

	public static void main(String[] args) {
		// String[] itenNames = {"Item Name","Order Quantity","Unit
		// Price","Total","Remark"};
		Shop shop = new Shop("src/shop_java/stock.csv");

		String sItem = shop.findProdInfo("Coke Can", 107);
		printItemDetails(sItem, false);
		//String[] items = sItem.split(",");
		//System.out.println("Items terurned: " + items.length);
		// for (int i = 0; i < items.length; i++) {
		// 	System.out.println(itenNames[i]+": "+items[i]);
		// } 
		
		// Customer james = new Customer("src/shop_java/customer.csv");
		// System.out.println(james);
	}
	
}
