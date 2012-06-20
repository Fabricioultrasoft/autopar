package autopar.controller;

/*
 * Thread Generica
 */

public class ThreadController extends Thread {
	private Object obj;
	private String method;
	
	public ThreadController (Object obj, String method) {
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
		System.out.println("Thread finalizada, classe: "+obj.getClass());
	}
	
	public void setObj (Object obj) {
		this.obj = obj;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}
