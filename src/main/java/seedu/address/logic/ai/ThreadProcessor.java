package seedu.address.logic.ai;

import java.util.ArrayList;
import java.util.HashSet;

import seedu.address.ui.MainWindow;

public class ThreadProcessor {
    private static final ArrayList<Thread> threadQueue = new ArrayList<>();
    private static final Thread superThread;
    private static final HashSet<Thread> runningThreads = new HashSet<>();

    static {
        superThread = new Thread(() -> {
            while (!MainWindow.isDone()) {
                while (!threadQueue.isEmpty()) {
                    Thread temp = threadQueue.remove(0);
                    temp.start();
                    runningThreads.add(temp);
                    try {
                        temp.join();
                    } catch (InterruptedException e) {
                        //runningThreads.remove(temp);
                    } finally {
                        removeThread(temp);
                    }
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
            stopAllThreads();
        });
    }

    /**
     * Adds the given Thread to queue
     *
     * @param thread the Thread to add
     */
    public static void addThread(Thread thread) {
        threadQueue.add(thread);
        threadQueue.sort((t1, t2) -> t2.getPriority() - t1.getPriority());
        if (!superThread.isAlive()) {
            superThread.start();
        }
    }

    /**
     * Removes the given Thread
     *
     * @param thread the Thread to remove
     */
    public static void removeThread(Thread thread) {
        runningThreads.remove(thread);
    }

    /**
     * Stops All Running Parallel Threads
     */
    public static void stopAllThreads() {
        threadQueue.removeIf(t -> true);
        runningThreads.forEach(Thread::interrupt);
        superThread.interrupt();
    }

    /**
     * Checks if all threads done running
     *
     * @return true, if all threads done
     */
    public static boolean isEmpty() {
        return threadQueue.isEmpty() && runningThreads.isEmpty();
    }

}
