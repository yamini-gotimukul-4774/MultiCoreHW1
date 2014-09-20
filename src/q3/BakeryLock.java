package q3;

// TODO
// Implement the bakery algorithm

public class BakeryLock implements MyLock {
	private volatile boolean[] flag;
	private volatile int[] label;
	private int numThread;

	public BakeryLock(int numThread) {
		flag = new boolean[numThread];
		label = new int[numThread];
		this.numThread = numThread;
		for (int i = 0; i < numThread; i++) {
			flag[i] = false;
			label[i] = 0;
		}
	}

	@Override
	public void lock(int myId) {
		flag[myId] = true;
		int temp = 0;
		for (int i = 0; i < numThread; i++) {
			if (temp < label[i]) {
				temp = label[i];
			}
		}
		label[myId] = temp + 1;
		
		//While there exist a process
		int i =0;
	/*	int count = 0; 
		while(i < numThread){
			if(flag[i] && i !=myId){
				count++;
			}
			if(count >= 1){
				if(label[myId] > label[i]){
					continue;
				}else if(label[myId] == label[i]){
					if(myId> i){
						continue;
					}else{
						break;
					}
				}
			}
			i++;
		}
		*/
		

		while (true) {
			for (; i < numThread; i++) {
				if (i != myId && flag[i]) {
					if (label[myId] > label[i]) {
						continue;
					} else if (label[myId] == label[i]) {
						if (myId > i) {
							continue;
						} else {
							break;
						}
					}

				}
			}
			if (i == numThread) {
				break;
			}
		}
	}

	@Override
	public void unlock(int myId) {
		flag[myId] = false;
	}
}
