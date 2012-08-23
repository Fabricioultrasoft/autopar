package autopar.model.flow;

import java.util.ArrayList;


public class Flow {
	ArrayList<FlowThread> threads;
	
	public Flow() {
		threads = new ArrayList<FlowThread>();
	}
	
	public void addThread(FlowThread ft) {
		threads.add(ft);
	}
	
	/**
	 * Varre as threads a procura de uma ainda rodando, e devolve sua msg para controle de progresso
	 * @return String
	 */
	public String getNextAliveThreadMsg() {
		for (FlowThread ft : threads) {
			if (ft.isAlive()) {
				return getMsg(ft.getMethod());
			}
		}
		return "Aguarde...";
	}
	
	private String getMsg(String method) {
		if (method.equals("loadWeb"))
			return "Carregando produtos da WEB...";
		if (method.equals("loadLocal"))
			return "Carregando produtos locais...";
		if (method.equals("updateWeb"))
			return "Verificando...";
		
		return "";
	}
}
