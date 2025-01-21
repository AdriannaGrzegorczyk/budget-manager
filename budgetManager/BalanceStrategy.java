package budgetManager;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class BalanceStrategy  extends  MenuStrategy{

    public BalanceStrategy(int order, String operationName) {
        super(order, operationName);
    }

    @Override
    boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income) {
        DecimalFormat decimalFormat = new DecimalFormat("$#0.00");
        String formattedAmount = decimalFormat.format(income.getIncome());
        System.out.println("Balance: " + formattedAmount);
        return false;
    }
}
