package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo1 {

    Lock      lock      = new ReentrantLock();
    Condition condition = lock.newCondition();

    int       flag      = 0;
    volatile int       change = 0;

    public static void main(String[] args) {
        ConditionDemo1 demo = new ConditionDemo1();
        new Thread(demo.new Tread1()).start();
        new Thread(demo.new Tread2()).start();
    }

    class Tread1 implements Runnable {

        @Override
        public void run() {
            while (flag < 3) {
                lock.lock();
                if(flag < 3){
                    if(change == 0){
                        System.out.println("thread1 == flag = " + flag);
                        try {
                            int n = flag * 3 + 1;
                            for (int i = n; i < n + 3; i++) {
                                System.out.println("1==== " + i);
                            }
                            flag++;
                            change = 1;
                            condition.signal();
                        } finally {
                            lock.unlock();
                        }
                    }else{
                        condition.awaitUninterruptibly();
                    }
                }
            }
        }
    }

    class Tread2 implements Runnable {

        @Override
        public void run() {
            while (flag < 3) {
                lock.lock();
                if(flag < 3){
                    if(change == 1){
                        System.out.println("thread1 == flag = " + flag);
                        try {
                            int n = flag * 3 + 1;
                            for (int i = n; i < n + 3; i++) {
                                System.out.println("2==== " + i);
                            }
                            flag++;
                            change = 0;
                            condition.signal();
                        } finally {
                            lock.unlock();
                        }
                    }else{
                        condition.awaitUninterruptibly();
                    }
                }
            }
        }

    }

}
