package autopar.model;

public class SubGrupo {
	
	private String codigoSubGrupo;
	private String nome;
	
	public SubGrupo (String codigoSubGrupo, String nome) {
		this.codigoSubGrupo = codigoSubGrupo;
		this.nome = nome;
	}
	
	public String getCodigoSubGrupo() {
		return codigoSubGrupo;
	}
	public void setCodigoSubGrupo(String codigoSubGrupo) {
		this.codigoSubGrupo = codigoSubGrupo;
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
			
			SubGrupo subGrupo = (SubGrupo) obj;
			if (subGrupo.getNome().equals(this.getNome()) 
					&& Integer.parseInt(subGrupo.getCodigoSubGrupo()) == Integer.parseInt(this.getCodigoSubGrupo()))
				return true;
		}
		return false;
	}
}
