package com.translationdata;

import org.junit.Test;

public class ThreeThreads {
	void startThreads() throws InterruptedException {
		Runnable runnable = () -> {
			System.out.printf("Hello from thread %s\n", Thread.currentThread().getName());
			System.out.printf("Thread %s completed.\n", Thread.currentThread().getName());
		};

		System.out.println("Starting threads");
		
		for(int i = 1; i <= 3; i++) {
			Thread thread = new Thread(runnable);
			thread.start();
		}
	}

	@Test
	public void threeThreads() throws InterruptedException {
		startThreads();
	}

}
