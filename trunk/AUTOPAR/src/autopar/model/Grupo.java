package autopar.model;

public class Grupo {
	
	private String codigoGrupo;
	private String nome;
	
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() == this.getClass()) {
			if (this == obj)
				return true;
			
			Grupo grupo = (Grupo) obj;
			if (grupo.getNome().equals(this.getNome()) && grupo.getCodigoGrupo().equals(this.getCodigoGrupo()))
				return true;
		}
		return false;
	}
	
}
