package budgetManager;

import java.util.List;
import java.util.Map;

public class BackStrategy extends MenuStrategy {
    public BackStrategy(int order, String operationName) {
        super(order, operationName);
    }

    @Override
    boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income) {
        return true;
    }


}
