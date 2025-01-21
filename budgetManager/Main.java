package budgetManager;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MainMenuStrategyProvider provider = new MainMenuStrategyProvider();
       List<Purchase> purchaseList = new LinkedList<>();
        IncomeWrapper incomeWrapper = new IncomeWrapper();

        while (true) {
            provider.printMenu();
            int numberFromMenu = scanner.nextInt();
            boolean flag = provider.executeProvider(numberFromMenu, purchaseList, incomeWrapper);
            if (flag) {
                break;
            }
        }
        System.out.println();
        System.out.println("Bye!");
    }
}
