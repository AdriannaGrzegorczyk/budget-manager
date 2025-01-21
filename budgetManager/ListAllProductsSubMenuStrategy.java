package budgetManager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static budgetManager.PurchaseSubMenuStrategy.strategyOptional;

public class ListAllProductsSubMenuStrategy extends MenuStrategy {

    Scanner scanner = new Scanner(System.in);

    List<MenuStrategy> subMenu = List.of(
            new ListAllProductsStrategy(1, "Food"),
            new ListAllProductsStrategy(2, "Clothes"),
            new ListAllProductsStrategy(3, "Entertainment"),
            new ListAllProductsStrategy(4, "Other"),
            new ListAllProductsStrategy(5,"All"),
            new BackStrategy(6, "Back")
    );

    public ListAllProductsSubMenuStrategy(int order, String operationName) {
        super(order, operationName);
    }

    @Override
    boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper wrapper) {
        while (true) {
            System.out.println("Choose the type of purchase");
            printMenu();
            int option = scanner.nextInt();
            boolean shouldExit =  executeProvider(option, purchaseList, wrapper);
            if (shouldExit){
                break;
            }
        }
        return false;
    }

    public void printMenu() {
        for (MenuStrategy menuStrategy : subMenu) {
            System.out.println(menuStrategy);
        }
    }

    public boolean executeProvider(int numberFromMenu,
                                   List<Purchase> purchaseList,
                                   IncomeWrapper incomeWrapper) {
        return strategyOptional(numberFromMenu, purchaseList, incomeWrapper, subMenu);
    }

}
