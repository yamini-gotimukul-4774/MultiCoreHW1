package q3;

public class MyThread extends Thread {
	
	Counter counter;
	
	public MyThread(String name, Counter counter){
		super(name);
		this.counter = counter;
	}
	
	public void run(){
		counter.increment();
		
	}

}
