package demo1;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

interface FriedChickenRestaurant {
    void setMealForSale(SetMeal setMeal);

    void bulkPurchase();
}

public class West2FriedChickenRestauran implements FriedChickenRestaurant {
    private double balance;
    private ArrayList<Beer> beersClass;
    private ArrayList<Juice> juicesClass;
    private static ArrayList<SetMeal> setMealsClass = new ArrayList<>();

    static {
        initSetMealsClass();
    }

    public static void initSetMealsClass() {
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
        setMealsClass.add(temp1);
        setMealsClass.add(temp2);
        setMealsClass.add(temp3);
        setMealsClass.add(temp4);
        setMealsClass.add(temp5);
    }

    public West2FriedChickenRestauran(double balance) {
        this.balance = balance;
        this.beersClass = new ArrayList<>();
        this.juicesClass = new ArrayList<>();
    }

    @Override
    public void setMealForSale(SetMeal setMeal) {               //卖东西
        int flag = 1;
        if (setMeal.getDrinkClass() instanceof Beer) {
            Beer a = (Beer) setMeal.getDrinkClass();
            try {
                use(a);
            } catch (IngredientSortOutException e) {
                flag = 0;
                e.printStackTrace();
            }
            if (setMealsClass.contains(setMeal)) {
                balance += setMeal.getprice();
                System.out.println(setMeal + "套餐售出一份" + "余额为：" + balance + "元");
            } else System.out.println(setMeal + "套餐储量不足");
        } else if (setMeal.getDrinkClass() instanceof Juice) {
            Juice a = (Juice) setMeal.getDrinkClass();
            try {
                use(a);
            } catch (IngredientSortOutException e) {
                flag = 0;
                e.printStackTrace();
            }
            if (setMealsClass.contains(setMeal)) {
                balance += setMeal.getprice();
                System.out.println(setMeal + "套餐售出一份" + "余额为：" + balance + "元");
            } else System.out.println(setMeal + "套餐储量不足");
        }
    }

    /**
     * 批量购买
     *
     * @throws OverdraftBalanceException
     */
    @Override
    public void bulkPurchase() throws OverdraftBalanceException {
        double purchaseCost;
        Scanner cin = new Scanner(System.in);
        Juice appleJuice = new Juice("苹果汁", 5.5, LocalDate.parse("2020-05-01"));
        Juice lemonJuice = new Juice("非柠不可", 10, LocalDate.parse("2020-05-05"));
        Juice bigLemonJuice = new Juice("非柠不可(大份装)", 25, LocalDate.parse("2020-05-05"));
        Beer baiwei = new Beer("百威啤酒", 6, LocalDate.parse("2020-05-20"), 3);
        Beer heiniken = new Beer("喜力啤酒", 6, LocalDate.parse("2020-05-20"), 3);
        System.out.println("您要购买苹果汁，柠檬汁,百威啤酒，喜力啤酒，柠檬汁（大瓶），各几瓶：");
        int a = cin.nextInt(), b = cin.nextInt(), c = cin.nextInt(), d = cin.nextInt(), e = cin.nextInt();
        purchaseCost = a * appleJuice.getCost() + b * lemonJuice.getCost() + c * baiwei.getCost()
                + d * heiniken.getCost() + e * bigLemonJuice.getCost();


        if (balance >= purchaseCost) {
            for (int i = 0; i < a; i++) juicesClass.add(appleJuice);
            for (int i = 0; i < b; i++) juicesClass.add(lemonJuice);
            for (int i = 0; i < c; i++) beersClass.add(baiwei);
            for (int i = 0; i < d; i++) beersClass.add(heiniken);
            for (int i = 0; i < e; i++) juicesClass.add(bigLemonJuice);
            balance -= purchaseCost;
            System.out.println("购买成功,余额剩余" + balance + "元");
        } else {
            throw new OverdraftBalanceException("余额不足，进货差" + (purchaseCost - balance) + "元");
        }

    }

    public boolean use(Beer beer) throws IngredientSortOutException {
        boolean flag;
        flag = true;
        if (beersClass.contains(beer)) {
            beersClass.remove(beer);
        } else {
            flag = false;
        }
        if (!flag) throw new IngredientSortOutException(beer.name + "已经售空,抱歉。");
        return flag;
    }

    public boolean use(Juice juice) throws IngredientSortOutException {
        boolean flag = true;
        if (juicesClass.contains(juice)) {
            juicesClass.remove(juice);
        } else {
            flag = false;
        }
        if (!flag) throw new IngredientSortOutException(juice.name + "已经售空,抱歉。");
        return flag;
    }

    public ArrayList<Beer> getBeersClass() {
        return beersClass;
    }

    public ArrayList<Juice> getJuicesClass() {
        return juicesClass;
    }

    public static ArrayList<SetMeal> getSetMealsClass() {
        return setMealsClass;
    }

}

abstract class Drinks {
    protected String name;
    protected double cost;
    protected LocalDate productionData;
    protected int qualityGuaranteeDay;

    public Drinks(String name, double cost, LocalDate productionData, int qualityGuaranteeDay) {
        this.name = name;
        this.cost = cost;
        this.productionData = productionData;
        this.qualityGuaranteeDay = qualityGuaranteeDay;
    }

    public boolean ifLoseQuality() {        //判断饮料是否过期
        LocalDate endData = productionData;
        endData.plusDays(qualityGuaranteeDay);
        return endData.isAfter(productionData);
    }

    public abstract String toString();

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public LocalDate getProductionData() {
        return productionData;
    }

    public int getQualityGuaranteeDay() {
        return qualityGuaranteeDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drinks drinks = (Drinks) o;
        return Double.compare(drinks.cost, cost) == 0 &&
                qualityGuaranteeDay == drinks.qualityGuaranteeDay &&
                Objects.equals(name, drinks.name) &&
                Objects.equals(productionData, drinks.productionData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, productionData, qualityGuaranteeDay);
    }
}

class Beer extends Drinks {
    private float proof;

    public Beer(String name, double cost, LocalDate productionData, float proof) {
        super(name, cost, productionData, 30);
        this.proof = proof;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", productionData=" + productionData +
                ", qualityGuaranteeDay=" + qualityGuaranteeDay +
                ", proof=" + proof +
                '}';
    }

    public float getProof() {
        return proof;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return Float.compare(beer.proof, proof) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(proof);
    }
}


class Juice extends Drinks {

    public Juice(String name, double cost, LocalDate productionData) {
        super(name, cost, productionData, 2);
    }

    @Override
    public boolean ifLoseQuality() {
        return super.ifLoseQuality();
    }

    @Override
    public String toString() {
        return "Juice{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", productionData=" + productionData +
                ", qualityGuaranteeDay=" + qualityGuaranteeDay +
                '}';
    }

}


class SetMeal {
    private String name;
    private double price;
    private String chickenName;
    private Drinks drinkClass;

    public SetMeal(String name, double price, String chickenName, Drinks drinkClass) {
        this.name = name;
        this.price = price;
        this.chickenName = chickenName;
        this.drinkClass = drinkClass;
    }

    @Override
    public String toString() {
        return "SetMeal{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", chickenName='" + chickenName + '\'' +
                ", drinkClass=" + drinkClass +
                '}';
    }

    public Drinks getDrinkClass() {
        return drinkClass;
    }

    public double getprice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getChickenName() {
        return chickenName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetMeal setMeal = (SetMeal) o;
        return Double.compare(setMeal.price, price) == 0 &&
                Objects.equals(name, setMeal.name) &&
                Objects.equals(chickenName, setMeal.chickenName) &&
                Objects.equals(drinkClass, setMeal.drinkClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, chickenName, drinkClass);
    }
}

class IngredientSortOutException extends RuntimeException {
    public IngredientSortOutException(String msg) {
        super(msg);
    }
}

class OverdraftBalanceException extends RuntimeException {
    public OverdraftBalanceException(String msg) {
        super(msg);
    }
}



/*
 * 为什么用ArrayList？
 * 向顾客出售套餐的同时要给与顾客（上帝）最好的服务，
 * 避免因西二炸鸡店生意太过火爆，导致顾客排队排得太长，等得太久，
 * 所以选择了查询更快的ArrayList，更快的提供套餐
 * LinkedList插入删除更快体现在进货出货的时候，牺牲自己进货出货过程的时间换取顾客的满意度，我感觉血赚！！！
 *   */