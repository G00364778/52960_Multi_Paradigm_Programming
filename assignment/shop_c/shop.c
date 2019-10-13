#include <stdio.h>
#include <string.h>
#include <stdlib.h>
char *banner = "--------------------------------------\n";

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
}

void printCustomer(struct Customer c)
{
	printf("\n\n%s\t\tCUSTOMER\n%s",banner,banner);
	printf("CUSTOMER NAME: %s \nCUSTOMER BUDGET: %.2f\n", c.name, c.budget);
	printf(banner);
	double sum = 0;
	for(int i = 0; i < c.index; i++)
	{
		printProduct(c.shoppingList[i].product);
		printf("QUANTITY:\t%d\n",c.shoppingList[i].quantity);
		double prod = c.shoppingList[i].quantity * c.shoppingList[i].product.price; 
		printf("TOTAL:\t\t%.2f\n\n", prod);
		sum+=prod;
	}
	printf("TOTAL COST:\t%.2f\n",sum);
	printf("BUDGET BALANCE:\t%.2f\n",c.budget-sum);
}

void printShop(struct Shop s)
{
	printf("\n\n%s\t\tSHOP\n%s",banner,banner);
	printf("CASH:\t\t€ %.2f", s.cash);
	printf("\n%s",banner);
	for (int i = 0; i < s.index; i++)
	{
		printProduct(s.stock[i].product);
		printf("STOCK LEVEL:\t%d ", s.stock[i].quantity);
		printf("\n\n");
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
	char *tokens[3];
	
	fp = fopen(csvfile, "r");
	if (fp == NULL)
			exit(EXIT_FAILURE);

	// read the csv file line by line in the while loop
	while ((read = getline(&line, &length, fp)) != -1) {
		int idx=0; // reset the tokens array index
		char *t = strtok(line, ","); // read the first value untill a comma is encountered
		// repeat reading untill a null charecter is encountered
		while ( t != NULL ){
			char *token = malloc(sizeof(char) * 25); //create a local token string variable
			strcpy(token,stripNewline(t)); // copy the value t to token after cleanup
			tokens[idx++]=token; // add all the tokens to the token array for further processing
			t = strtok(NULL, ",");//read the next token untill null
		}
		// if the customer name was read in, run this code
		if (strstr(tokens[0],"Name") != NULL|strstr(tokens[0],"name") != NULL)
		{
			// add the customer name to the customer structure variable name
			customer.name = tokens[1];
		}
		// otherwise if the budget is returned execute this code
		else if (strstr(tokens[0],"Budget") != NULL|strstr(tokens[0],"budget") != NULL)
		{
			double budget = atof(tokens[1]);// convert the budget value to a float
			// and assign it to the customer structure 
			customer.budget = budget;
		}
		//otherwise add the shopping list quantity and cost to the customer in a structured way
		//and increment the index
		else
		{
			int n = atoi(tokens[2]); // convert the number of items to an interger value
			double cost = atof(tokens[1]);// convert the cost to a floating point value
			//create a product structure with a name and cost
			struct Product product = { tokens[0], cost };
			//and add the product structure and number of the items to the shopping list
			struct ProductStock shopList = { product, n };
			//and update the shoppinglist with the content while also incrementing the index
			customer.shoppingList[customer.index++] = shopList;
		}
	}	
	//and when the loops conclude return the customer structre to the calling function
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
	struct Shop shop = createAndStockShop();
	printShop(shop);
	struct Customer customer = createAndLoadShoppingList("order.csv");
	printCustomer(customer);


	
  return 0;
}