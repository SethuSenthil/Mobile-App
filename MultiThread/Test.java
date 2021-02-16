import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    static int i = 0;
    static AtomicInteger ai;
    static int otherI = 0;

    static synchronized void add(int number) {
        otherI += number;
    }

    public static void main(String[] args) {

        ai = new AtomicInteger(0);
        for (int i = 0; i < 200; i++) {
            Thread t = new MyThread();
            t.start();
            try {
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("i" + i);
            System.out.println("ai" + ai);
            System.out.println("other" + otherI);

        }
    }

    static class MyThread extends Thread {
        public void run() {

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            ai.getAndAdd(1);
            i++;
            add(1);
        }
    }
}