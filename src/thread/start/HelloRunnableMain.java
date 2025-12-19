package thread.start;

public class HelloRunnableMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloRunnable runnable = new HelloRunnable();
        Thread thread = new Thread(runnable); // runnable 객체를 넘겨서 생성 - 작업을 스레드에 실행하도록 runnable  전달한다
        thread.start();

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
