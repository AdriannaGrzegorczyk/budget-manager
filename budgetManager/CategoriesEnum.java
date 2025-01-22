package budgetManager;

public enum CategoriesEnum {

    FOOD("Food"),
    CLOTHES("Clothes"),
    ENTERTAINMENT("Entertainment"),
    OTHER("Other");

    String nameOfCategory;

    CategoriesEnum(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public static CategoriesEnum getEnumByName(String input) {
        for (CategoriesEnum categoriesEnum : values()) {
            if (categoriesEnum.nameOfCategory.equals(input)) {
                return categoriesEnum;
            }
        }
        throw new RuntimeException();
    }
}
