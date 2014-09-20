package q3;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Counter counter = null;
		MyLock lock;
		long executeTimeMS = 0;
		int numThread = 6;
		int numTotalInc = 1200000;

		if (args.length < 3) {
			System.err.println("Provide 3 arguments");
			System.err.println("\t(1) <algorithm>: fast/bakery/synchronized/"
					+ "reentrant");
			System.err.println("\t(2) <numThread>: the number of test thread");
			System.err.println("\t(3) <numTotalInc>: the total number of "
					+ "increment operations performed");
			System.exit(-1);
		}

		if (args[0].equals("fast")) {
			lock = new FastMutexLock(numThread);
			counter = new LockCounter(lock);
		} else if (args[0].equals("bakery")) {
			lock = new BakeryLock(numThread);
			counter = new LockCounter(lock);
		} else if (args[0].equals("synchronized")) {
			counter = new SynchronizedCounter();
		} else if (args[0].equals("reentrant")) {
			counter = new ReentrantCounter();
		} else {
			System.err.println("ERROR: no such algorithm implemented");
			System.exit(-1);
		}

		numThread = Integer.parseInt(args[1]);
		numTotalInc = Integer.parseInt(args[2]);

		// TODO
		// Please create numThread threads to increment the counter
		// Each thread executes numTotalInc/numThread increments
		// Please calculate the total execute time in millisecond and store the
		// result in executeTimeMS

		// Creating a numThread threads to increment the counter
		//executeTimeMS = System.currentTimeMillis();
		List<MyThread> threads = new ArrayList<MyThread>();
		for (int i = 0; i < numThread; i++) {
			MyThread t = new MyThread(String.valueOf(i), counter, numThread,
					executeTimeMS);
			threads.add(t);
			threads.get(i).start();
		}
		try {
			for (int i = 0; i < numThread; i++) {
				threads.get(i).join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//executeTimeMS = System.currentTimeMillis() - executeTimeMS;
		executeTimeMS= threads.get(0).getExecuteTimeMS();
		System.out.println(executeTimeMS);
		System.out.println("Count "+ counter.getCount());
	}

	public static class MyThread extends Thread {

		private volatile Counter counter;
		private volatile long startTime;
		private volatile long endTime;
		private volatile long intervel;
		private int numTotalInc = 1200000;
		private long executeTimeMS;
		int max;

		public MyThread(String name, Counter counter, int numThread,
				long executeTimeMS) {
			super(name);
			this.counter = counter;
			this.max = numTotalInc / numThread;
			this.executeTimeMS = executeTimeMS;
		}

		public void run() {
		startTime = System.currentTimeMillis();
		System.out.println("Max value"+ max);
			System.out.println("Thread - "+ Thread.currentThread().getName() );
			for (int i = 0; i < max; i++) {
				counter.increment();
			}
		endTime = System.currentTimeMillis();
		intervel = endTime - startTime;
		executeTimeMS += intervel;
		}

		public long getStartTime() {
			return startTime;
		}

		public void setStartTime(long startTime) {
			this.startTime = startTime;
		}

		public long getEndTime() {
			return endTime;
		}

		public void setEndTime(long endTime) {
			this.endTime = endTime;
		}

		public long getIntervel() {
			return intervel;
		}

		public void setIntervel(long intervel) {
			this.intervel = intervel;
		}

		public long getExecuteTimeMS() {
			return executeTimeMS;
		}

		public void setExecuteTimeMS(long executeTimeMS) {
			this.executeTimeMS = executeTimeMS;
		}

	}
}
