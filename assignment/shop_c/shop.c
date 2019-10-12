#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct Product {
	char* name;
	double price;
};

struct ProductStock {
	struct Product product;
	int quantity;
};

struct Shop {
	double cash;
	struct ProductStock stock[10];
	int index;
};

struct Customer {
	char* name;
	double budget;
	struct ProductStock shoppingList[10];
	int index;
};

void printProduct(struct Product p)
{
	printf("PRODUCT NAME:\t%s \nPRODUCT PRICE:\t%.2f\n", p.name, p.price);
	// printf("-------------\n");
}

void printCustomer(struct Customer c)
{
	printf("CUSTOMER NAME: %s \nCUSTOMER BUDGET: %.2f\n", c.name, c.budget);
	printf("-------------\n");
	for(int i = 0; i < c.index; i++)
	{
		printProduct(c.shoppingList[i].product);
		printf("%s ORDERS %d OF ABOVE PRODUCT\n", c.name, c.shoppingList[i].quantity);
		double cost = c.shoppingList[i].quantity * c.shoppingList[i].product.price; 
		printf("The cost to %s will be €%.2f\n", c.name, cost);
	}
}

void printShop(struct Shop s)
{
	printf("\n\nCASH:\t\t€ %.2f", s.cash);
	printf("\n---------------------------------\n\n");
	for (int i = 0; i < s.index; i++)
	{
		printProduct(s.stock[i].product);
		printf("STOCK LEVEL:\t%d ", s.stock[i].quantity);
		printf("\n---------------------------------\n\n");
	}
}

const char * stripNewline(char *textStr){
	// strip off \n character
	if (textStr[strlen(textStr)-1] == '\n'){
		textStr[strlen(textStr)-1] = '\0';
	}
	// strip off \r character
	if (textStr[strlen(textStr)-1] == '\r'){
		textStr[strlen(textStr)-1] = '\0';
	}
	// strip leading white space
	while(isspace((unsigned char)*textStr)) textStr++;

	return textStr;
} 

struct Customer createAndLoadShoppingList(char *csvfile){
	struct Customer customer = { 0 };
	FILE * fp;
	char * line = NULL;
	size_t length = 0;
	ssize_t read;

	fp = fopen(csvfile, "r");
	if (fp == NULL)
			exit(EXIT_FAILURE);

	while ((read = getline(&line, &length, fp)) != -1) {
		//printf("LEN:\t%zu:\n", read);
		//printf("LINE: %s (len:%zu)\n", line, read);
		char *t = strtok(line, ",");
		char *v = strtok(NULL, ",");
		char *tag = malloc(sizeof(char) * 25);
		char *val = malloc(sizeof(char) * 25);
		//strcpy(tag, t);
		//strcpy(val, v);
		//tag = stripNewline(t);
		//val = stripNewline(v);
		strcpy(tag,stripNewline(t));
		strcpy(val,stripNewline(v));
		//if the customer name is returned execute this code		
		if (strstr(tag,"Name") != NULL|strstr(tag,"name") != NULL)
		{
			//printf("%s:\t%s\n",t,v);
			customer.name = val;
		}
		//otherwise if the budget is returned execute this code
		else if (strstr(tag,"Budget") != NULL|strstr(tag,"budget") != NULL)
		{
			double budget = atof(val);
			//printf("%s:\t%.2f\n",tag,val);
			customer.budget = budget;
		}
		//otherwise add the shopping list quantity to the customer is a strctured way
		//and increment the index
		else
		{
			int n = atoi(val);
			//printf("%s:\t%i\n",t,n);
			struct Product product = { tag };
			struct ProductStock shopList = { product, n };
			customer.shoppingList[customer.index++] = shopList;
		}
	}	

	return customer;
}


struct Shop createAndStockShop()
{
	struct Shop shop = { 0 };
	FILE * fp;
	char * line = NULL;
	size_t len = 0;
	ssize_t read;

	fp = fopen("stock.csv", "r");
	if (fp == NULL)
			exit(EXIT_FAILURE);

	while ((read = getline(&line, &len, fp)) != -1) {
			// printf("Retrieved line of length %zu:\n", read);
			// printf("%s IS A LINE", line);
	if (strstr(line,"cash") != NULL|strstr(line,"Cash") != NULL){
		char *n = strtok(line, ",");
		char *c = strtok(NULL, ",");
		double cash = atof(c);
		shop.cash = cash;
	}
	else {
		char *n = strtok(line, ",");
		char *p = strtok(NULL, ",");
		char *q = strtok(NULL, ",");
		int quantity = atoi(q);
		double price = atof(p);
		char *name = malloc(sizeof(char) * 50);
		strcpy(name, n);
		//prepare product and stockItems structures
		struct Product product = { name, price };
		struct ProductStock stockItem = { product, quantity };
		//two things happen here, shop.tock at index[0] is updated but also incremented and 
		// is updated with value stockItem.
		shop.stock[shop.index++] = stockItem;
		// printf("NAME OF PRODUCT %s PRICE %.2f QUANTITY %d\n", name, price, quantity);
	}
	
	}
	
	return shop;
}



int main(void) 
{
	//struct Customer dominic = { "Dominic", 100.0 };
	//
	// struct Product coke = { "Can Coke", 1.10 };
	// struct Product bread = { "Bread", 0.7 };
	// // printProduct(coke);
	//
	// struct ProductStock cokeStock = { coke, 20 };
	// struct ProductStock breadStock = { bread, 2 };
	//
	// dominic.shoppingList[dominic.index++] = cokeStock;
	// dominic.shoppingList[dominic.index++] = breadStock;
	//
	// printCustomer(dominic);
	
	struct Shop shop = createAndStockShop();
	//printShop(shop);
	struct Customer customer = createAndLoadShoppingList("order.csv");
	//printCustomer(customer);

	
	// printf("The shop has %d of the product %s\n", cokeStock.quantity, cokeStock.product.name);
	
  return 0;
}