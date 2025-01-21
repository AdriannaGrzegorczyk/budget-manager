package budgetManager;

import java.util.*;

import static budgetManager.PurchaseSubMenuStrategy.strategyOptional;

public class SortingSubMenuSpecificTypeStrategy extends MenuStrategy{

    Scanner scanner = new Scanner(System.in);
    List<MenuStrategy> subMenu = List.of(
            new SortBySpecificType(1, "Food"),
            new SortBySpecificType(2, "Clothes"),
            new SortBySpecificType(3,"Entertainment"),
            new SortBySpecificType(4, "Other")
    );
    public SortingSubMenuSpecificTypeStrategy(int order, String operationName) {
        super(order, operationName);

    }

    @Override
    boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income) {
        while (true) {
            System.out.println("Choose the type of purchase");
            printMenu();
            int option = scanner.nextInt();
            boolean shouldExit =  executeProvider(option, purchaseList, income);
            if (shouldExit){
                break;
            }
        }
        return false;
    }

    public boolean executeProvider(int numberFromMenu,
                                   List<Purchase> purchaseList,
                                   IncomeWrapper incomeWrapper) {
        return strategyOptional(numberFromMenu, purchaseList, incomeWrapper, subMenu);
    }
    public static class SortBySpecificType extends MenuStrategy {
        CategoriesEnum categoriesEnum;
        public SortBySpecificType(int order, String operationName) {
            super(order, operationName);
            categoriesEnum =  CategoriesEnum.getEnumByName(this.operationName);
        }

        @Override
        boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income) {
            if  (purchaseList.isEmpty()){
                System.out.println("The purchase list is empty!");
                return false;
            }
            purchaseList.stream()
                    .filter(purchase -> purchase.getType() == categoriesEnum)
                    .sorted(Comparator.comparing(Purchase::getPrice))
                    .forEach(purchase -> System.out.println(purchase.getName() + " " + purchase.getPrice()));
            return false;
        }

    }

    private void printMenu() {
        for (MenuStrategy menuStrategy : subMenu) {
            System.out.println(menuStrategy);
        }
    }
}