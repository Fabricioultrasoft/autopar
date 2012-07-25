package autopar.model;

import java.util.ArrayList;

public class Produto {
	
	private String codigo;
	private String nome;
	private String descricao; //BLOB => byte?
	private String preco;
	private String codigoMarca;
	private String codigoGrupo;
	private String codigoSubGrupo;
	
	private Marca marca;
	private Grupo grupo;
	private SubGrupo subGrupo;
	
	private ArrayList<String> imagens;
	
	public Produto(String codigo, String nome) {
		this.nome = nome;
		this.codigo = codigo;
		
		imagens = new ArrayList<String>();
	}
	
	public Produto(String codigo, String nome, String descricao, String preco, String codigoMarca, String codigoSubGrupo, String codigoGrupo) {
		this.nome = nome;
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.codigoMarca = codigoMarca;
		this.codigoSubGrupo = codigoSubGrupo;
		this.codigoGrupo = codigoGrupo;
		
		imagens = new ArrayList<String>();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * Sobreescrevendo o metodo para checar se é igual
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() == this.getClass()) {
			if (this == obj)
				return true;
			
			Produto p = (Produto) obj;
			if ((this.codigo+this.codigoGrupo+this.codigoMarca+this.codigoSubGrupo+this.descricao+this.nome+this.preco)
				.equals(p.codigo+p.codigoGrupo+p.codigoMarca+p.codigoSubGrupo+p.descricao+p.nome+p.preco))
				return true;		
		}
		return false;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public SubGrupo getSubGrupo() {
		return subGrupo;
	}
	public void setSubGrupo(SubGrupo subGrupo) {
		this.subGrupo = subGrupo;
	}
	public String getCodigoMarca() {
		return codigoMarca;
	}
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	public String getCodigoSubGrupo() {
		return codigoSubGrupo;
	}
	public void setCodigoSubGrupo(String codigoSubGrupo) {
		this.codigoSubGrupo = codigoSubGrupo;
	}
	
	public int getImagens() {
		return getQtdImagens();
	}
	
	public int getQtdImagens() {
		return imagens.size();
	}
	public void addImagem(String s) {
		imagens.add(s);
	}
	public void removeImagem(String s) {
		imagens.remove(s);
	}
}
