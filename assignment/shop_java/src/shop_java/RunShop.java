package shop_java;

import java.util.ArrayList;

// import java.io.IOException;
// import java.nio.charset.StandardCharsets;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;

public class RunShop {

  public RunShop() {
    // private ArrayList<ProductStock> s;
    Shop shop = new Shop("src/shop_java/stock.csv");
    Customer cust = new Customer("src/shop_java/customer.csv");
    double budget = cust.getBudget();
    String name = cust.getName();

    String item = shop.findProdInfo("Spaghetti", 10);
    shop.printItemDetails(item, true);
    //cust.getShoppingList()
    //https://stackoverflow.com/questions/14699439/how-can-i-search-through-an-arraylist-and-match-it-to-another-method
    //for (ProductStock item : stock) {
    //  System.out.println(item);
    //}
    //cust.getShoppingList();
  }

  public static void main(String[] args) {
      new RunShop();
  }
}
