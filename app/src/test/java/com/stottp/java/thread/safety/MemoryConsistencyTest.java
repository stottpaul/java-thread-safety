package com.stottp.java.thread.safety;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class MemoryConsistencyTest {

    @Test
    void name() throws InterruptedException {
        new StopThread().run();
    }

    public static class StopThread {
        private boolean stopRequested = false;

        public void run() throws InterruptedException {
            Thread backgroundThread = new Thread(() -> {
                int i = 0;
                while (!stopRequested)
                    i++;
            });
            backgroundThread.start();

            TimeUnit.SECONDS.sleep(1);
            stopRequested = true;

            // Wait for the background thread to stop before continuing
            backgroundThread.join();
        }
    }
}
