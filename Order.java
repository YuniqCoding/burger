package burger;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> products;
    private int num;
    private static int orderNum = 1;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Order(){
        products = new ArrayList<>();
        this.num = Order.orderNum++;
    }
    public void addItem(Product product){
        products.add(product);
    }
    public void clearItem(){
        products.clear();
    }
}
