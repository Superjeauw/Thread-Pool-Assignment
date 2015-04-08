import java.util.Vector;

public class Results extends Thread{
	
	private Vector<ReportT1> report_T1;
	private Vector<ReportT2> report_T2;
	int t1_size, t2_size;
	
	public Results(int _t1_size,int _t2_size){
		t1_size = _t1_size;
		t2_size = _t2_size;
		report_T1 = new Vector<ReportT1>();
		report_T2 = new Vector<ReportT2>();
		this.start();
	}
	
	
	public void report(Task t) {
		if(t instanceof T_1){
			report_T1.addElement(new ReportT1((T_1)t));
		}else{
			report_T2.addElement(new ReportT2((T_2)t));
		}
	//this.notify();
	}
	
	@Override
	public void run() {
		System.out.println("print all expressions of type (1.1) first)");
		while(t1_size > 0){
			while(!report_T1.isEmpty()){
				t1_size--;
				System.out.println(report_T1.remove(0));
			}
		}
		System.out.println("print all expressions of type (1.2) second");
		while(t2_size > 0){
			while(!report_T2.isEmpty()){
				System.out.println(report_T2.remove(0));
				t2_size--;
			}
		}
	}
	
	private class ReportT1{
		double result;
		int mSize;
		String print;
		
		private ReportT1(T_1 t){
			mSize = t.getmSize();
			result = t.report();
			print = "Expr. Type (1.1), n = "+mSize+": "+result;
		}
		
		@Override
		public String toString() {
			return print;
		}
	}
	private class ReportT2{
		double result;
		int mSize,sSize;
		String print;
		
		private ReportT2(T_2 t){
			mSize = t.getmSize();
			sSize = t.getsSize();
			result = t.report();
			
			if(mSize == sSize){
				print = "Expr. Type (1.2), l = m = "+sSize+": "+result;
			}else {
				print = "Expr. Type (1.2), l = "+mSize+", m = "+sSize+": "+result;
			}
		}
		@Override
		public String toString() {
			return print;
		}
	}

}
