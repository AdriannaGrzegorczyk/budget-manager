package budgetManager;

import java.text.DecimalFormat;
import java.util.List;


public class ListAllProductsStrategy extends MenuStrategy {

    CategoriesEnum categoriesEnum;

    public ListAllProductsStrategy(int order, String operationName) {
        super(order, operationName);
        if (!operationName.equals("All")) {
            categoriesEnum = CategoriesEnum.getEnumByName(this.operationName);
        }
    }

    @Override
    boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income) {

        if (categoriesEnum == null) {
            System.out.println("All:");
            for (Purchase purchase : purchaseList) {

                DecimalFormat decimalFormat = new DecimalFormat("$#0.00");
                String formattedAmount = decimalFormat.format(purchase.getPrice());

                System.out.println(purchase.getName() + " " + formattedAmount);
            }

            Double sum = purchaseList.stream().map(Purchase::getPrice).mapToDouble(Double::doubleValue).sum();

            System.out.printf("Total sum: $%.2f", sum);
            System.out.println();
        } else {
            System.out.println(categoriesEnum.getNameOfCategory() + ":");
            for (Purchase purchase : purchaseList) {
                if (purchase.getType().equals(categoriesEnum)) {

                    DecimalFormat decimalFormat = new DecimalFormat("$#0.00");
                    String formattedAmount = decimalFormat.format(purchase.getPrice());

                    System.out.println(purchase.getName() + " " + formattedAmount);
                }
            }

            Double sum = purchaseList.stream().filter(purchase -> purchase.getType()
                            .equals(categoriesEnum))
                    .map(Purchase::getPrice).mapToDouble(Double::doubleValue).sum();

            System.out.printf("Total sum: $%.2f", sum);
            System.out.println();
        }
        return false;

    }
}