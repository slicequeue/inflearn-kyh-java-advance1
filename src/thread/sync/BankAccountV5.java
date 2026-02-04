package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV5 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV5(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) { // 임계 영역
        log("거래 시작: " + getClass().getSimpleName());

        if (!lock.tryLock()) { // 그냥 대기 안하는 상황
            log("[진입 실패] 이미 처리중인 작업이 있습니다.");
            return false;
        }

        try {
            // == 임계 영역 시작 ==
            log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
            if (balance < amount) {
                log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
                return false;
            }
            // 잔고가 출금액 보다 많으면, 진행
            log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
            sleep(1000); // 출금에 걸리는 시간으로 가정
            balance = balance - amount;
            log("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);
            // == 임계 영역 종료 ==

        } finally {
            lock.unlock(); // ReentrantLock 이용하여 lock 해제 !!!! 반드시 언락을 걸어줘야함 !!!!
            // 안걸면 큰일남! 쓰레드가 계속 웨이팅 상태로 남을 수 있다
        }

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock(); // !! ReentrantLock 이용하여 lock을 걸기 !!
        try {
            return balance;
        } finally {
            lock.unlock(); // ReentrantLock 이용하여 lock 해제
        }
    }
}
