package thread.start.quiz;

import static util.MyLogger.log;

public class StartTest1Main {
    static class CounterThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                log(String.format("value: %s", (i + 1)));
                delay(1000);
            }
        }

        private void delay(int msec) {
            try {
                sleep(msec);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        log("start");
        CounterThread counterThread = new CounterThread();
        counterThread.start();
        log("end");
    }
}
