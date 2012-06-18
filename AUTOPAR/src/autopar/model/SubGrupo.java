package autopar.model;

public class SubGrupo {
	
	private Grupo grupoPai;
	private String nome;
	
	public Grupo getGrupoPai() {
		return grupoPai;
	}
	public void setGrupoPai(Grupo grupoPai) {
		this.grupoPai = grupoPai;
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
			
			SubGrupo subGrupo = (SubGrupo) obj;
			if (subGrupo.getNome().equals(this.getNome()) && subGrupo.getGrupoPai().equals(this.getGrupoPai()))
				return true;
		}
		return false;
	}
	
}
