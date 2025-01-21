package budgetManager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PurchaseSubMenuStrategy extends MenuStrategy {

    Scanner scanner = new Scanner(System.in);
    List<MenuStrategy> subMenu = List.of(
            new PurchaseStrategy (1,"Food"),
            new PurchaseStrategy (2,"Clothes"),
            new PurchaseStrategy (3,"Entertainment"),
            new PurchaseStrategy (4,"Other"),
            new BackStrategy (5,"Back")
    );
    public PurchaseSubMenuStrategy(int order, String operationName) {
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

    static boolean strategyOptional(int numberFromMenu,
                                    List<Purchase> purchaseList,
                                    IncomeWrapper incomeWrapper,
                                    List<MenuStrategy> subMenu) {

        Optional<MenuStrategy> strategy = subMenu
                .stream().filter(option -> option.getOrder() == numberFromMenu).findFirst();
        if (strategy.isPresent()) {
            if(!(strategy.get() instanceof BackStrategy)){
                System.out.println();
            }
            boolean result = strategy.get().executeStrategy(purchaseList, incomeWrapper);
            if(!(strategy.get() instanceof BackStrategy)){
                System.out.println();
            }
            return result;
        } else {
            System.out.println("Invalid option.");
            return false;
        }
    }

}
