package org.example;

import java.util.concurrent.BlockingQueue;

public class NumbersConsumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int poisonPill;

    public NumbersConsumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    public void run() {
        try {
            System.out.println("Consumer: " + Thread.currentThread().getName());
            while (true) {
                Integer number = queue.take();
                if (number.equals(poisonPill)) {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " result: " + number);
                System.out.println("Remaining capacity: " + queue.remainingCapacity());

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

