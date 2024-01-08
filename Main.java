package burger;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Order order= new Order();
    public static ArrayList<Menu> shakeshakeMenu = makeMenu("SHAKESHACK");
    public static ArrayList<Menu> orderMenu = makeMenu("ORDER");
    public static ArrayList<Menu> burgersMenu = makeMenu("Burgers");

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        while (true){
            try{
                showMainMenu();
            }catch (BadInputException e){
                System.out.println(e.getMessage());
                break;
            }catch (InputMismatchException e){
                System.out.println("숫자를 입력해주세요!");
                break;
            }
        }


    }

    private static void showMainMenu() throws InterruptedException, BadInputException {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println();
        System.out.println("[ SHAKESHACK MENU ]");
        for(Menu menu : shakeshakeMenu) menu.showMenuInfo();
        System.out.println();
        System.out.println("[ ORDER MENU ]");
        for(Menu menu : orderMenu) menu.showMenuInfo();
        int mainNum = sc.nextInt();
        if (mainNum <= shakeshakeMenu.size()) {
            showSubMenu(shakeshakeMenu.get(mainNum - 1).getName());
        }else if(mainNum == shakeshakeMenu.size()+1){
            showOrderMenu();
        }else if(mainNum == shakeshakeMenu.size()+2){
            showCancelMenu();
        }else{
            throw new BadInputException(shakeshakeMenu.size()+2);
        }
    }

    private static void showCancelMenu() throws BadInputException {
        System.out.println("진행하던 주문을 취소하시겠습니까?\n");
        System.out.println("1. 확인        2. 취소");
        int choice = sc.nextInt();
        if(choice==1) {
            order.clearItem();
            System.out.println("진행하던 주문이 취소되었습니다.");
            System.out.println();
        }else if(choice>2){
            throw new BadInputException(2);
        }
    }

    private static void showOrderMenu() throws InterruptedException, BadInputException {
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ Orders ]");
        double total= 0.0;
        for(Product product : order.getProducts()) {
            product.showMenuInfo();
            total +=product.getPrice();
        }
        System.out.println();
        System.out.println("[ Total ]");
        System.out.println("W "+total);
        System.out.println();
        System.out.println("1. 주문      2. 메뉴판");
        int choice = sc.nextInt();
        if(choice==1){
            order.clearItem();
            System.out.println("주문이 완료되었습니다!");
            System.out.println("대기번호는 [ "+order.getNum() +" ] 번 입니다.");
            System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
            System.out.println();
            Thread.sleep(3000);
        }else if(choice>2){
            throw new BadInputException(2);
        }
    }

    private static void showSubMenu(String mainPick) throws BadInputException {
        System.out.println( "SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.");
        System.out.println();
        ArrayList<Menu> subMenu = new ArrayList<>();
        System.out.println("[ "+mainPick+" MENU ]");
        switch (mainPick){
            case "Burgers":
                subMenu = burgersMenu;
        }
        for(Menu menu : subMenu) menu.showMenuInfo();

        int choice = sc.nextInt();
        putBasket((Product)subMenu.get(choice-1));
    }

    private static void putBasket(Product product) throws BadInputException {
        product.showMenuInfo();
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if(choice == 1){
            order.addItem(product);
            System.out.println(product.getName() +" 가 장바구니에 추가되었습니다.");
            System.out.println();
        }else if(choice>2){
            throw new BadInputException(2);
        }
    }

    private static ArrayList<Menu> makeMenu(String title){

        ArrayList<Menu> menu = new ArrayList<>();
        switch (title){
            case "SHAKESHACK":
                menu.add(new Menu("Burgers","앵거스 비프 통살을 다져만든 버거"));
                menu.add(new Menu("Forzen Custard","매장에서 신선하게 만드는 아이스크림"));
                menu.add(new Menu("Drinks","매장에서 직접 만드는 음료"));
                menu.add(new Menu("Beer","뉴욕 브루클린 브루어리에서 양조한 맥주"));
                break;
            case "ORDER":
                menu.add(new Menu("Order","장바구니를 확인 후 주문합니다."));
                menu.add(new Menu("Cancel","진행중인 주문을 취소합니다."));
                break;
            case "Burgers":
                menu.add(new Product("ShackBurger","토마토, 양상추, 쉑소스가 토핑된 치즈버거",6.9));
                menu.add(new Product("SmokeShack","베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거",8.9));
                menu.add(new Product("Shroom Burger","몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거",9.4));
                menu.add(new Product("Cheeseburger","포테이토 번과 비프패티, 치즈가 토핑된 치즈버거",6.9));
                menu.add(new Product("Hamburger","비프패티를 기반으로 야채가 들어간 기본버거",5.4));
        }
        return menu;

    }
}
