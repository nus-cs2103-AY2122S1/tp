package seedu.address.logic.ai;

import java.util.HashSet;

public class ThreadProcessor {
    private static final HashSet<Thread> threadQueue = new HashSet<>();

    /**
     * Adds the given Thread to queue
     *
     * @param thread the Thread to add
     */
    public static void addThread(Thread thread) {
        threadQueue.add(thread);
        thread.start();
    }

    public static void removeThread(Thread thread) {
        threadQueue.remove(thread);
    }

    /**
     * Stops All Running Parallel Threads
     */
    public static void stopAllThreads() {
        threadQueue.parallelStream().forEach(Thread::interrupt);
    }

    /**
     * Checks if all threads done running
     *
     * @return true, if all threads done
     */
    public static boolean isEmpty() {
        return threadQueue.isEmpty();
    }

}
