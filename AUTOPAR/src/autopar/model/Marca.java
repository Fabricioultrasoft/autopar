package autopar.model;

public class Marca {
	
	private String codigoMarca;
	private String nome;
	
	public Marca(String codigoMarca, String nome) {
		this.codigoMarca = codigoMarca;
		this.nome = nome;
	}
	
	public String getCodigoMarca() {
		return codigoMarca;
	}
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	public String getNome() {
		return nome.replaceAll("[\\\\']", "\\\\'");
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() == this.getClass()) {
			if (this == obj)
				return true;
			
			Marca marca = (Marca) obj;
			if (marca.getNome().equals(this.getNome()) 
					&& Integer.parseInt(marca.getCodigoMarca()) == Integer.parseInt(this.getCodigoMarca()))
				return true;
		}
		return false;
	}
}
