package thread.control;

import thread.start.HelloThread;

import static util.MyLogger.log;

public class ThreadInfoMain {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);
        log("mainThread.threadId() = " + mainThread.threadId());                // 유일값 ID 자바 운영체제에서 부여 충돌 X
        log("mainThread.getName() = " + mainThread.getName());                  // 이름은 중복 가능
        log("mainThread.getPriority() = " + mainThread.getPriority());          // 높을 수록 좀 더 실행! 항상은 아님 (백엔드 개발시 이 것을 수정할 일은 거의 없음
        log("mainThread.getThreadGroup() = " + mainThread.getThreadGroup());    // 스레드 그룹! - 이후 설명
        log("mainThread.getState() = " + mainThread.getState());                // 실행 가능한 상태 확인 => RUNNABLE

        Thread myThread = new Thread(new HelloThread(), "myThread");
        log("myThread = " + myThread);
        log("myThread.threadId() = " + myThread.threadId());                // 유일값 ID 자바 운영체제에서 부여 충돌 X
        log("myThread.getName() = " + myThread.getName());                  // 이름은 중복 가능
        log("myThread.getPriority() = " + myThread.getPriority());          // 높을 수록 좀 더 실행! 항상은 아님 (백엔드 개발시 이 것을 수정할 일은 거의 없음
        log("myThread.getThreadGroup() = " + myThread.getThreadGroup());    // 스레드 그룹! - 이후 설명
        log("myThread.getState() = " + myThread.getState());                // 실행 가능한 상태 확인 => NEW
    }
}
