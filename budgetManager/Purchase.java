package budgetManager;

public class Purchase {
    private String name;
    private CategoriesEnum type;
    private Double price;

    public Purchase(String name, CategoriesEnum type, Double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public CategoriesEnum getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }


}
