package Pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
	private LinkedBlockingQueue<Runnable> tasks;
	private List<Thread> threads;

	public ThreadPool(int size) {
		threads = new ArrayList<>();
		tasks = new LinkedBlockingQueue<Runnable>();
		Thread t;
		for (int i = 0; i < size; i++) {
			(t = new Thread(this::run)).start();
			threads.add(t);
		}
	}

	public void addTask(Runnable runnable){
		try {
			tasks.put(runnable);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void run() {
		while(!Thread.interrupted()){
			try {
				tasks.take().run();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static int count=0;
	public static void main(String[] args) {
		ThreadPool tp = new ThreadPool(10);
		
		Runnable run = ()->{
			System.out.println(Thread.currentThread().getName()+"fuck");
			System.out.println(count++);
		};
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tp.addTask(run);
		}
	}
}
