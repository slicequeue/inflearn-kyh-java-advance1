package thread.control;

import static util.MyLogger.log;

public class ThreadStateMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable(), "myThread");
        log("myThread.state1 = " + thread.getState()); // NEW
        log("myThread.start()");
        thread.start();
        Thread.sleep(1000);
        log("myThread.state3 = " + thread.getState()); // TIMED_WAITING sleep 상태는 main 스레드가 확인
        Thread.sleep(4000);
        log("myThread.state5 = " + thread.getState()); // TERMINATED
        log("end");

    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {

            try {
                log("start");
                Thread thread = Thread.currentThread();
                log("myThread.state2 = " + thread.getState()); // RUNNABLE
                log("sleep() start");
                Thread.sleep(3000); // myThread
                log("sleep() end");
                log("myThread.state4 = " + thread.getState()); // RUNNABLE
                log("end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
