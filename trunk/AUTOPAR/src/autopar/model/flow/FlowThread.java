package autopar.model.flow;


/*
 * Thread Generica
 */

public class FlowThread extends Thread {
	private Object obj;
	private String method;
	private Flow flow;
	
	public FlowThread (Object obj, String method) {
		this.obj = obj;
		this.method = method;
		flow = autopar.Main.flow;
		flow.addThread(this);
	}
	
	@Override
	public void run () {
		try {
			obj.getClass().getMethod(method).invoke(obj);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (method.equals("loadWeb") || method.equals("loadLocal"))
			autopar.Main.splashController
			.setProgress(flow.getNextAliveThreadMsg(), 0);
		else
			autopar.Main.splashController
			.setProgress(flow.getNextAliveThreadMsg(), 10);
	}
	
	public void setObj (Object obj) {
		this.obj = obj;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMethod() {
		return method;
	}
}
