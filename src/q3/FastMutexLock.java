package q3;

// TODO 
// Implement Fast Mutex Algorithm
public class FastMutexLock implements MyLock {
	private volatile int x = -1, y = -1;
	private volatile boolean[] flag;

	public FastMutexLock(int numThread) {
		flag = new boolean[numThread];
		for (int i = 0; i < numThread ; i++) {
			flag[i] = false;
		}
	}

	@Override
	public void lock(int myId) {
		while (true) {
			flag[myId] = true;
			x = myId;
			if (y != -1) {
				flag[myId] = false;
				while (y != -1) {
					// looping continuously;
				}
				continue;
			} else {
				y = myId;
				if (x == myId) {
					return;
				} else {
					flag[myId] = false;
					while (true) {
						int count = 0;
						for (int i = 0; i < flag.length; i++) {
							if (!flag[i]) {
								count++;
							}
						}
						if (count == flag.length) {
							break;
						}
					}
					if (y == myId) {
						return;
					} else {
						while (y != -1) {
							// waiting for the door to get open
						}
						continue;
					}
				}

			}
		}

	}

	@Override
	public void unlock(int myId) {
		y = -1;
		flag[myId] = false; 
	}
}
