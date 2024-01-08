package burger;

public class Product extends Menu{
    private double price;
    private int num;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private static int totalNum = 1;

    public Product(String name, String description, double price){
        super(name,description);
        this.price = price;
        this.num = Product.totalNum++;
    }

    @Override
    public void showMenuInfo() {
        System.out.print(num+". ");
        System.out.print(String.format("%-20s", super.getName()));
        System.out.print(String.format("| W "));
        System.out.print(price);
        System.out.print(String.format(" | "));
        System.out.println(String.format("%-20s",super.getDescription()));
    }
}
