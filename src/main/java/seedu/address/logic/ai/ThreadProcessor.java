package seedu.address.logic.ai;

import java.util.HashSet;

public class ThreadProcessor {
    private static final Thread supervisor;
    private static final HashSet<Thread> threadQueue = new HashSet<>();

    static {
        supervisor = new Thread(() -> {
            if (!threadQueue.isEmpty()) {
                threadQueue.parallelStream().forEach(thread -> {
                    if (!thread.isAlive()) {
                        threadQueue.remove(thread);
                    }
                });
            }
        });
        supervisor.start();
    }

    public static void addThread(Thread thread) {
        threadQueue.add(thread);
    }

    public static void stopAllThreads() {
        threadQueue.parallelStream().forEach(Thread::interrupt);
        supervisor.interrupt();
    }

}
