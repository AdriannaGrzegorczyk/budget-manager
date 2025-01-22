package budgetManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileStrategyLoad extends MenuStrategy {
    public FileStrategyLoad(int order, String operationName) {
        super(order, operationName);
    }

    @Override
    boolean executeStrategy(List<Purchase> purchaseList, IncomeWrapper income) {

        try {
            List<String> content = Files.readAllLines(Paths.get("purchases.txt"));
            Double inc = Double.parseDouble(content.get(0));
            income.setIncome(inc);

            for (int i = 1; i < content.size(); i++) {
                String[] arr = content.get(i).split(";");
                purchaseList.add(new Purchase(arr[1], CategoriesEnum.valueOf(arr[0]), Double.parseDouble(arr[2])));
            }
            System.out.println("Purchases were loaded!");

        } catch (IOException e) {
            System.out.println("Invalid option");
        }
        return false;
    }
}
