package budgetManager;

import java.util.List;
import java.util.Map;

public abstract class MenuStrategy {

    protected int order;

    protected String operationName;

    public MenuStrategy(int order, String operationName) {
        this.order = order;
        this.operationName = operationName;
    }

    public int getOrder() {
        return order;
    }

    public String getOperationName() {
        return operationName;
    }

    abstract boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income);

    @Override
    public String toString() {
        return order + ") " + operationName;
    }
}
