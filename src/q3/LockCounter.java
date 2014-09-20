package q3;

// TODO
// Use MyLock to protect the count

public class LockCounter extends Counter {
	
	private MyLock lock;
	
    public LockCounter(MyLock lock) {
    	super();
    	this.lock = lock;
    }

    @Override
    public void increment() {
    	int myId= Integer.valueOf(Thread.currentThread().getName());
    	lock.lock(myId);
    	count++;
    	lock.unlock(myId);
    }

    @Override
    public int getCount() {
        return count;
    }
}
