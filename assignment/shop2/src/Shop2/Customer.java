package Shop2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;

public class Customer {
	
	private String name;
	private double budget;
	private ArrayList<ProductStock> shoppingList;
	
	public Customer(String fileName) {
		shoppingList = new ArrayList<>();
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			String[] firstLine = lines.get(0).split(",");
			name = firstLine[0];
			budget = Double.parseDouble(firstLine[1]);
			// i am removing at index 0 as it is the only one treated differently
			lines.remove(0);
			for (String line : lines) {
				String[] arr = line.split(",");
				String name = arr[0];
				int quantity = Integer.parseInt(arr[1].trim());
				Product p = new Product(name, 0);
				ProductStock s = new ProductStock(p, quantity);
				shoppingList.add(s);
			}
			
		}

		catch (IOException e) {

			// do something
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}


	public double getBudget() {
		return budget;
	}


	public ArrayList<ProductStock> getShoppingList() {
		return shoppingList;
	}


	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
	  Date date = new Date();
		String dts = dateFormat.format(date); 
		
		String ret = "\nCUSTOMER: "+ name + "\n  BUDGET: " + String.format("%.2f",budget) + "\n";
		ret+=dts+"\n"; 
		for (ProductStock productStock : shoppingList) {
			ret+=String.format("%-12s x %3d\n",productStock.getProduct().getName(), productStock.getQuantity());
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Customer james = new Customer("src/Shop2/customer.csv");
		System.out.println(james);
	}
}
