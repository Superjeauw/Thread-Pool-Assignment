import java.util.ArrayList;

public class Feeder extends Thread{

	PoolManager pm;
	ArrayList<Task> allTasks;
	
	Feeder(PoolManager pm,ArrayList<Task> _allTasks){
		super("Feeder");
		this.pm = pm;
		allTasks = _allTasks;
		this.start();
	}
	
	public void run(){
		int s=0;
		for (int n = 0; n < allTasks.size(); n++) {
			boolean flag = pm.addTask(allTasks.get(n));
			if(!flag){
				n--;
				try {
					synchronized (pm) {pm.wait();}
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}
}