package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        t.start();

        sleep(1000);

        task.flag = false;
        log("flag = " + task.flag + ", count = " + task.count + " in main");
    }

    static class MyTask implements Runnable {

//        boolean flag = true;
//        long count;
        /* [결과 - volatile 사용 안했을 때]
            23:22:49.933 [     work] flag = true, count = 100000000 in while()
            23:22:50.059 [     work] flag = true, count = 200000000 in while()
            23:22:50.183 [     work] flag = true, count = 300000000 in while()
            23:22:50.302 [     work] flag = true, count = 400000000 in while()
            23:22:50.420 [     work] flag = true, count = 500000000 in while()
            23:22:50.528 [     work] flag = true, count = 600000000 in while()
            23:22:50.636 [     work] flag = true, count = 700000000 in while()
            23:22:50.742 [     work] flag = true, count = 800000000 in while()
            23:22:50.801 [     main] flag = false, count = 854184801 in main
            23:22:50.849 [     work] flag = true, count = 900000000 in while() // <- 이 부분! work 스레드 즉, CPU 캐시 반영 안된 것!
            23:22:50.850 [     work] flag = false, count = 900000000 종료 // 콘솔에 결과를 출력하면서 스레드가 잠시 대기 후 캐시 메모리 값 갱신되었을
            확률이 높다.
        * */

        volatile boolean flag = true;
        volatile long count;
        /* [결과 - volatile 사용 했을 때]
            23:25:44.629 [     work] flag = true, count = 100000000 in while()
            23:25:44.814 [     work] flag = true, count = 200000000 in while()
            23:25:44.990 [     work] flag = true, count = 300000000 in while()
            23:25:45.163 [     work] flag = true, count = 400000000 in while()
            23:25:45.333 [     work] flag = true, count = 500000000 in while()
            23:25:45.436 [     main] flag = false, count = 559560045 in main
            23:25:45.436 [     work] flag = false, count = 559560045 종료 // 성능도 확실히 느려짐! 무조건 main 메모리 접근해야 이 컴퓨터는 약 2배
             정도 성능 차이가 남 따라서 꼭! 필요할때만 사용할 것!!!
        * */

        @Override
        public void run() {
            while (flag) {
                count++;
                // 1억번에 한번씩 출력
                if (count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count + " in while()");
                }
            }
            log("flag = " + flag + ", count = " + count + " 종료");
        }
    }
}
