package budgetManager;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IncomeSrategy extends MenuStrategy {
    public IncomeSrategy(int order, String operationName) {
        super(order, operationName);
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper wrapper) {
        System.out.println("Enter income:");
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 0) {
            wrapper.setIncome(0);
        } else {
            wrapper.setIncome(wrapper.getIncome() + input);
        }
        System.out.println("Income was added!");
        return false;
    }
}
