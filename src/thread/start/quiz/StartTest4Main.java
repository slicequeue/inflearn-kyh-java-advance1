package thread.start.quiz;

import static util.MyLogger.log;

public class StartTest4Main {

    public static void main(String[] args) {

        log("start");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    log("A");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    log("B");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread1.setName("Thread-A");
        thread1.start();

        thread2.setName("Thread-B");
        thread2.start();
        log("end");
    }
}
