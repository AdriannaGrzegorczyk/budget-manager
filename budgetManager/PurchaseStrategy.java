package budgetManager;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class PurchaseStrategy extends MenuStrategy {
    CategoriesEnum categoriesEnum;
    Scanner scanner = new Scanner(System.in);

    public PurchaseStrategy(int order, String operationName) {
        super(order, operationName);
        categoriesEnum = CategoriesEnum.getEnumByName(this.operationName);
    }

    @Override
    boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income) {
        System.out.println("Enter purchase name:");
        String productName = scanner.nextLine();
        System.out.println("Enter its price: ");

        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Purchase was added!");
        Purchase purchase = new Purchase(productName, this.categoriesEnum, price);
        purchaseList.add(purchase);
        income.setIncome(income.getIncome() - price);

        return false;
    }
}
