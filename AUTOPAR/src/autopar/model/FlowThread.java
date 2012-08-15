package autopar.model;

/*
 * Thread Generica
 */

public class FlowThread extends Thread {
	private Object obj;
	private String method;
	
	public FlowThread (Object obj, String method) {
		this.obj = obj;
		this.method = method;
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
		//System.out.println("Thread finalizada, classe: "+obj.getClass());
		autopar.Main.splashController
		.setProgress("Ok! Verificando...", 10);
	}
	
	public void setObj (Object obj) {
		this.obj = obj;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}
