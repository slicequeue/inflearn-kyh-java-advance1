package thread.start;

public class BadThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start"); // 메인 메서드는 대체 누가 실행하는 것일까? => main

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전"); // 메인 메서드는 대체 누가 실행하는 것일까? => main
        helloThread.run(); // run() 직접 실행
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후"); // 메인 메서드는 대체 누가 실행하는 것일까? => main

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
