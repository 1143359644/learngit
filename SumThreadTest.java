package demo2;

import java.util.Scanner;
import java.util.ArrayList;

public class SumThreadTest {
    public static void main(String[] args) {
        int j = 0;
        long startTime = System.currentTimeMillis();    //start
        Sum sum = new Sum(0, 0);
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        sum.setTarget(x);
        ArrayList<SumThread> threadArray = new ArrayList<>();
        Thread.currentThread().setPriority(10);
        for (long i = 100000000; i <= 1000000000; i += 100000000) {
            SumThread temp = new SumThread(sum, i, 100000000);
            temp.setPriority(1);
            threadArray.add(temp);
        }
        for(;j<threadArray.size();j++)
            threadArray.get(j).start();
        for (SumThread thread : threadArray) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();  //end
        System.out.println(sum.getAns());
        System.out.println("时间" + (endTime - startTime));
    }
}
