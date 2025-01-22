package budgetManager;

import java.io.*;
import java.util.List;

public class FileStrategySave extends MenuStrategy {
    public FileStrategySave(int order, String operationName) {
        super(order, operationName);
    }

    @Override
    boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income) {

        try (BufferedWriter myWriter = new BufferedWriter(new FileWriter("purchases.txt"))) {
            myWriter.write(Double.toString(income.getIncome()));
            myWriter.newLine();
            for (Purchase purchase : purchaseList) {
                myWriter.write(purchase.getType() + ";" + purchase.getName() + ";" + purchase.getPrice());
                myWriter.newLine();
            }

            System.out.println("Purchases were saved!");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Invalid option");
        }
        return false;
    }
}
