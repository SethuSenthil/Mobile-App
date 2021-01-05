public class ThreadTest {
    public static void main(String[] args) {

        Thread t1 = new Thread(" *Thread 1 *") {
            public void run() {
                for (int i = 0; i < 10; i++)
                    System.out.println(getName());
            }
        };

        Thread t2 = new Thread(" *Thread 2 *") {
            public void run() {
                for (int i = 0; i < 10; i++)
                    System.out.println(getName());
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++)
            System.out.println("*Main Thread*");

    }

}