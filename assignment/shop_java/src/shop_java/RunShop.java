package shop_java;

//import java.io.Console;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class RunShop {

  public RunShop(Shop shop, Customer cust) {
    /**
     * The RunShop funcion requires the Shop and Customer classes passed in to
     * iterate through the customer shopping list, find and verify srock levels 
     * in the shop class and print a receipt with a total and while doing so 
     * subtract inventory from the shop and add the cash received. 
     * 
     */
    DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
	  Date date = new Date();
	  String dts = dateFormat.format(date); // 2016/11/16 12:08:43
    // private ArrayList<ProductStock> s;
    String hdr="\n==================================";
    double budget = cust.getBudget();
    String name = cust.getName();
    double listTotal = 0;
    System.out.printf("%s\nNAME: %s BUDGET: %5s\n%s%s",hdr,name,budget,dts,hdr);
    ArrayList<ProductStock> sList = cust.getShoppingList();
    for (ProductStock productStock : sList) {
      int sListQuentity = productStock.getQuantity();
      String sListItem = productStock.getProduct().getName();
      String item = shop.findProdInfo(sListItem, sListQuentity);
      double itemTotal = Shop.printItemDetails(item, false);
      listTotal += itemTotal;
    }
    double cash = shop.getCash();
    shop.setCash(cash+listTotal);
    System.out.printf(hdr);
    System.out.printf("\nTOTAL %28.2f", listTotal);
    System.out.printf("\nBUDGET %27.2f", budget);
    System.out.printf("\nBALANCE %26.2f\n", budget-listTotal);
  }

  public static int shopSaveToCSV(Shop shop, String shopCSVFile) {
    /**
     * The shopSaveToCSV saves the current Shop class in memory to a
     * CSV files passed into the function call and retruns zero or 
     * negative when an exception occured writing the file.
     */
    try {
      FileWriter writer = new FileWriter(shopCSVFile);
      //FileWriter writer = new FileWriter(shopCSVFile);
      double cash = shop.getCash();
      writer.append("Cash, " + String.format("%.2f",cash) + "\n");
      ArrayList<ProductStock> stock = shop.getStock();
      for (ProductStock productStock : stock) {
        int quantity = productStock.getQuantity();
        String name = productStock.getProduct().getName();
        double price = productStock.getProduct().getPrice();
        writer.append(name + ", " + String.format("%.2f",price) + ", " + quantity + "\n");
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
    System.out.print("Welcome to our shop "+ name + " your budget is : " + budget);
    shop.printInvenrtory();
    Boolean finished = false;
    while (!finished) {
      System.out.print("Enter the name of the item you want: ");
      String item = scan.next();
      System.out.print("Enter the quantity of item you want: ");
      String quantity = scan.next();
      System.out.printf("%s x %s\n", item, quantity);
      shoppingList.add(String.format("%s,%s",item, quantity));
      System.out.print("Add another item(y/n): ");
      String more = scan.next();
      while(more.equalsIgnoreCase("y") && more.equalsIgnoreCase("n")){
        System.out.print("Add another item(y/n): ");
        more = scan.next();
      }
      if (more.equalsIgnoreCase("n")==true){finished=true;}
    }
    scan.close();
    return shoppingList;
  }

  public static void main(String[] args) {
    Boolean interacive = true;
    Boolean autosaveOnExit = true;

    Shop shop = new Shop("src/shop_java/stock.csv");

    if (interacive == true) {
      ArrayList<String> list = shopInteractive(shop);
      System.out.println(list);
    } else {
      Customer cust = new Customer("src/shop_java/customer.csv");
      new RunShop(shop, cust);
      shop.printInvenrtory();
    }
    if (autosaveOnExit==true) {
      shopSaveToCSV(shop, "src/shop_java/stock.csv");  
    }
  }
}
