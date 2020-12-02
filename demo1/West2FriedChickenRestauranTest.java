package demo1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class West2FriedChickenRestauranTest {
    public static void main(String[] args) {
        West2FriedChickenRestauran newRestauran = new West2FriedChickenRestauran(5000);

        Juice appleJuice = new Juice("苹果汁", 5.5, LocalDate.parse("2020-05-01"));
        Juice lemonJuice = new Juice("非柠不可", 10, LocalDate.parse("2020-05-05"));
        Juice bigLemonJuice = new Juice("非柠不可(大份装)", 25, LocalDate.parse("2020-05-05"));
        Beer baiwei = new Beer("百威啤酒", 6, LocalDate.parse("2020-05-20"), 3);
        Beer heiniken = new Beer("喜力啤酒", 6, LocalDate.parse("2020-05-20"), 3);
        SetMeal temp1 = new SetMeal("鸡翅吃到爽", 30, "5对辣翅", baiwei);
        SetMeal temp2 = new SetMeal("叫了只全鸡", 25, "1只炸全鸡", heiniken);
        SetMeal temp3 = new SetMeal("鸡腿吃到爽", 30, "5根大鸡腿", appleJuice);
        SetMeal temp4 = new SetMeal("超值团购3人餐", 85, "2只炸全鸡，3根大鸡腿，3对辣翅", bigLemonJuice);
        SetMeal temp5 = new SetMeal("轻享单人餐", 12, "1根大鸡腿，一对辣翅", lemonJuice);

        System.out.println("饮料列表：");
        appleJuice.toString();
        lemonJuice.toString();
        bigLemonJuice.toString();
        baiwei.toString();
        heiniken.toString();

        //西二炸鸡店菜单
        System.out.println("炸鸡店套餐：");
        ArrayList<SetMeal> ip = West2FriedChickenRestauran.getSetMealsClass();
        Iterator<SetMeal> it = ip.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        try {
            newRestauran.bulkPurchase();
            //500 500 500 500 500 --测试是否买得起原料
        } catch (OverdraftBalanceException e) {
            e.printStackTrace();
        }

        try {
            newRestauran.bulkPurchase();
            //5 5 5 5 5
        } catch (OverdraftBalanceException e) {
            e.printStackTrace();
        }


        /*
         *出售各个套餐各两份
         */
        newRestauran.setMealForSale(temp3);
        newRestauran.setMealForSale(temp3);
        newRestauran.setMealForSale(temp2);
        newRestauran.setMealForSale(temp2);
        newRestauran.setMealForSale(temp1);
        newRestauran.setMealForSale(temp1);
        newRestauran.setMealForSale(temp4);
        newRestauran.setMealForSale(temp4);
        newRestauran.setMealForSale(temp5);
        newRestauran.setMealForSale(temp5);
        newRestauran.setMealForSale(temp5);
        newRestauran.setMealForSale(temp5);
        newRestauran.setMealForSale(temp5);
        newRestauran.setMealForSale(temp5);

    }
}
