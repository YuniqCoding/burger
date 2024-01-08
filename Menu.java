package burger;

public class Menu {
    private String name;
    private String description;
    private int num;
    private static int totalNum = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Menu(String name, String description){
        this.name = name;
        this.description = description;
        this.num = Menu.totalNum++;
    }

    public void showMenuInfo(){
        System.out.print(num+". ");
        System.out.print(String.format("%-20s",name));
        System.out.print(String.format("| "));
        System.out.println(String.format("%-20s",description));
    }

}
