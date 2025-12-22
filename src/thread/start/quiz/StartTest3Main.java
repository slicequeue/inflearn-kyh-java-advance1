package thread.start.quiz;

import static util.MyLogger.log;

public class StartTest3Main {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    log(String.format("value: %s", (i + 1)));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        log("start");
        Thread thread = new Thread(runnable);
        thread.setName("counter");
        thread.start();
        log("end");
    }
}
