package thread.start.quiz;

import static util.MyLogger.log;

public class StartTest2Main {

    static class CounterRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                log(String.format("value: %s", (i + 1)));
                delay(1000);
            }
        }

        private void delay(int msec) {
            try {
                Thread.sleep(msec);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        log("start");
        Runnable runnable = new CounterRunnable();
        Thread thread = new Thread(runnable);
        thread.setName("counter");
        thread.start();
        log("end");
    }
}
