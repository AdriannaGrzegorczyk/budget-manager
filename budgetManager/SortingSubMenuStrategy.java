package budgetManager;

import java.util.*;
import java.util.stream.Collectors;

import static budgetManager.PurchaseSubMenuStrategy.strategyOptional;
import static java.util.stream.Collectors.groupingBy;

public class SortingSubMenuStrategy extends MenuStrategy {
    Scanner scanner = new Scanner(System.in);

    List<MenuStrategy> subMenu = List.of(
            new SortAll(1, "Sort all purchases"),
            new SortByType(2, "Sort by type"),
            new SortingSubMenuSpecificTypeStrategy(3, "Sort certain type"),
            new BackStrategy(4, "Back")
    );

    public SortingSubMenuStrategy(int order, String operationName) {
        super(order, operationName);
    }

    @Override
    boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income) {

        while (true) {
            System.out.println("How do you want to sort?");
            printMenu();
            int option = scanner.nextInt();
            boolean shouldExit = executeProvider(option, purchaseList, income);
            if (shouldExit) {
                break;
            }
        }
        return false;
    }

    private void printMenu() {
        for (MenuStrategy menuStrategy : subMenu) {
            System.out.println(menuStrategy);
        }
    }

    public boolean executeProvider(int numberFromMenu,
                                   List<Purchase> purchaseList,
                                   IncomeWrapper incomeWrapper) {
        return strategyOptional(numberFromMenu, purchaseList, incomeWrapper, subMenu);
    }


    public static class SortAll extends MenuStrategy {
        public SortAll(int order, String operationName) {
            super(order, operationName);
        }

        @Override
        boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income) {
            if  (purchaseList.isEmpty()){
                System.out.println("The purchase list is empty!");
                return false;
            }
            purchaseList.sort(Comparator.comparing(Purchase::getPrice));
            System.out.println("All:");
            for (Purchase purchase : purchaseList){
                System.out.println(purchase.getName() + " " + purchase.getPrice());
            }
            return false;
        }


    }

    public static class SortByType extends MenuStrategy {
        public SortByType(int order, String operationName) {
            super(order, operationName);
        }
//
        @Override
        boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income) {
            if  (purchaseList.isEmpty()){
                System.out.println("The purchase list is empty!");
                return false;
            }

            // SELECT categoryType, sum(Price) FROM PRODUCT  GROUP BY  categoryPurchase ORDER BY categoryPurchase DESC
            Map<CategoriesEnum, Double> map = purchaseList.stream().collect(groupingBy(Purchase::getType))
                    .entrySet().stream() // Map<Enum List<Purchase>>
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().mapToDouble(Purchase::getPrice).sum()))
                    // Map<Enum Double>
                    .entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));

            System.out.println("Types:");
            map.entrySet().forEach(type -> System.out.println(type.getKey() + " - " + type.getValue()));
            return false;
        }
    }

}
