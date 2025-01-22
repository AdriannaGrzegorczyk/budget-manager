package budgetManager;

import java.util.List;

import static budgetManager.PurchaseSubMenuStrategy.strategyOptional;

public class MainMenuStrategyProvider {

    List<MenuStrategy> options = List.of(
            new IncomeSrategy(1, "Add income"),
            new PurchaseSubMenuStrategy(2, "Add purchase"),
            new ListAllProductsSubMenuStrategy(3, "Show list of purchases"),
            new BalanceStrategy(4, "Balance"),
            new FileStrategySave(5, "Save"),
            new FileStrategyLoad(6, "Load"),
            new SortingSubMenuStrategy(7, "Analyze (sort)"),
            new BackStrategy(0, "Exit")
    );


    public void printMenu() {
        System.out.println("Choose your action:");
        for (MenuStrategy menuStrategy : options) {
            System.out.println(menuStrategy);
        }
    }

    public boolean executeProvider(int numberFromMenu,
                                   List<Purchase> purchaseList,
                                   IncomeWrapper incomeWrapper) {
        return strategyOptional(numberFromMenu, purchaseList, incomeWrapper, options);
    }

}
