package demo2;

import java.lang.Thread;

public class SumThread extends Thread {
    private Sum sum;
    private long right;
    private long interval;

    public SumThread(Sum sum, long right, long interval) {
        this.sum = sum;
        this.right = right;
        this.interval = interval;
    }

    public Sum getSum() {
        return sum;
    }

    public void setSum(Sum sum) {
        this.sum = sum;
    }

    public long getRight() {
        return right;
    }

    public void setRight(long right) {
        this.right = right;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        //run方法的执行表示取含有sum.target的数字之和
        long ans = 0;
        for (long i = right - interval; i < right; i++) {
            if (String.valueOf(i).contains(String.valueOf(sum.getTarget()))) ans += i;
        }
        synchronized (sum) {
            sum.setAns(sum.getAns() + ans);
        }
    }
}
