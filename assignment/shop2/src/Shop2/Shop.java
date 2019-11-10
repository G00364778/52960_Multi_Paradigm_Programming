package Shop2;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Shop {

	private double cash;
	private ArrayList<ProductStock> stock;

	public Shop(String fileName) {
		stock = new ArrayList<>();
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			// System.out.println(lines.get(0));
			cash = Double.parseDouble(lines.get(0));
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

	public void setCash(double cash) {
		this.cash = cash;
	}

	public ArrayList<ProductStock> getStock() {
		return stock;
	}

	@Override
	public String toString() {
		// return "Shop [cash=" + cash + ", stock=" + stock + "]";
		return String.format("Cash: %.2f\n%s", cash, stock);
	}

	public String getItemDetails(String ItemName) {
		String ret = "";
		for (ProductStock productStock : stock) {
			if (productStock.getProduct().getName().contains(ItemName)) {
				String product = productStock.getProduct().getName();
				int quantity = productStock.getQuantity();
				double price = productStock.getProduct().getPrice();
				ret = String.format("%s, %.2f, %d", product, price, quantity);
			}
		}
		return ret;
	}



	public static int shopSaveToCSV(Shop shop, String shopCSVFile) {
		/**
		 * The shopSaveToCSV saves the current Shop class in memory to a CSV files
		 * passed into the function call and retruns zero or negative when an exception
		 * occured writing the file.
		 */
		try {
			FileWriter writer = new FileWriter(shopCSVFile);
			// FileWriter writer = new FileWriter(shopCSVFile);
			double cash = shop.getCash();
			writer.append("" + String.format("%.2f", cash) + "\n");
			ArrayList<ProductStock> stock = shop.getStock();
			for (ProductStock productStock : stock) {
				int quantity = productStock.getQuantity();
				String name = productStock.getProduct().getName();
				double price = productStock.getProduct().getPrice();
				writer.append(name + ", " + String.format("%.2f", price) + ", " + quantity + "\n");
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	public static ArrayList<String> shopInteractive(Shop shop) {
		ArrayList<String> shoppingList = new ArrayList<String>();

		System.out.printf("\nWelcome to the interactive shop.\n");
		Scanner scan = new Scanner(System.in).useDelimiter("\r\n");
		System.out.print("Enter your name please: ");
		String name = scan.next();
		System.out.print("Enter your budget amount please: ");
		String budget = scan.next();
		shoppingList.add(String.format("%s,%s", name, budget));
		System.out.print("Welcome to our shop " + name + " your budget is : " + budget + "\n");
		// shop.printInvenrtory();
		Boolean finished = false;
		while (!finished) {
			System.out.print("Enter the name of the item you want: ");
			String item = scan.next();
			System.out.print("Enter the quantity of item you want: ");
			String quantity = scan.next();
			System.out.printf("%s x %s\n", item, quantity);
			shoppingList.add(String.format("%s,%s", item, quantity));
			System.out.print("Add another item(y/n): ");
			String more = scan.next();
			while (more.equalsIgnoreCase("y") && more.equalsIgnoreCase("n")) {
				System.out.print("Add another item(y/n): ");
				more = scan.next();
			}
			if (more.equalsIgnoreCase("n") == true) {
				finished = true;
			}
		}
		scan.close();
		try {
			FileWriter writer = new FileWriter("src/Shop2/interactive.csv");
			Boolean BeenHere = false;
			for (String string : shoppingList) {
				System.out.println(string);
				if (BeenHere == false) {
					writer.append(string);
					BeenHere = true;
				} else {
					writer.append("\n" + string);
				}
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shoppingList;
	}

	public void SetStockQuantty(String ItemName, int SetQuantity) {
		for (ProductStock productStock : stock) {
			if (productStock.getProduct().getName().contains(ItemName)) {
				productStock.setQuantity(SetQuantity);
			}
		}
	}

	public static void main(String[] args) {
		Boolean autosaveOnExit = true;
		Boolean interacive = true;
		String shoppingList = "src/Shop2/customer.csv";
		ArrayList<String> receipt = new ArrayList<String>();
		Shop shop = new Shop("src/Shop2/stock.csv");
		System.out.println(shop);
		// if interactive create a new list and the run the rest.
		if (interacive == true) {
			/** */
			ArrayList<String> list = shopInteractive(shop);
			System.out.println(list);
			shoppingList = "src/Shop2/interactive.csv";
		}
		
		Customer cust = new Customer(shoppingList);
		// System.out.println(cust);
		double custBudget = cust.getBudget();
		String custName = cust.getName();
		double total = 0;
		receipt.add(String.format("\nCUSTOMER: %s\nBUDGET: %.2f\n", custName, custBudget));
		ArrayList<ProductStock> list = cust.getShoppingList();
		for (ProductStock listStock : list) {
			// get name and quantity from shopping list
			String listName = listStock.getProduct().getName();
			int listQuantity = listStock.getQuantity();
			// then find the item details is the shop
			String ret = shop.getItemDetails(listName);
			String[] found = ret.split(","); // split the returned strings to values
			String name = found[0].trim();
			double price = Double.parseDouble(found[1].trim());
			int quantity = Integer.parseInt(found[2].trim());
			// System.out.printf("%s %f %d\n",name,price,quantity);
			int deficit = 0;
			if (quantity > listQuantity) { // if the quantity wanted is available assign it
				/* do nothong */
				deficit = 0;
			} else {// otherwise assigne the balance of the only
				deficit = listQuantity - quantity;
				listQuantity = quantity;
			}
			// update the receipt with the name, quantity and amount
			String msg = "";
			Double lineTotal = listQuantity * price;
			if (deficit != 0 ) {
				msg=String.format("- %d short",deficit);
			}
			receipt.add(String.format("%-15s x %3d @ %.2f = %.2f %s", name, listQuantity, price, lineTotal, msg));
			// subtract from the stock
			// productStock.setQuantity(quantity - listQuantity);
			shop.SetStockQuantty(listName, quantity - listQuantity);
			// and add to the cash
			double val = shop.getCash();
			val += lineTotal;
			shop.setCash(val);
			total += lineTotal;
		}
		receipt.add(String.format("\n TOTAL:               %6.2f", total));
		receipt.add(String.format("CHANGE:               %6.2f\n", custBudget-total));
		
		for (String line : receipt) {
			System.out.println(line);
		}
		System.out.println(shop);
		// save the shop to a file
		if (autosaveOnExit == true) {
			shopSaveToCSV(shop, "src/shop2/stock.csv");
		}

	}


}
