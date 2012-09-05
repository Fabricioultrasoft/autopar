package autopar.model;

import java.util.ArrayList;

import javax.swing.JCheckBox;

public class Produto {
	
	private String codigo;
	private String nome;
	private String descricao; //BLOB => byte?
	private String preco;
	private String codigoMarca;
	private String codigoGrupo;
	private String codigoSubGrupo;
	private int destaque;
	private int contadorImagens;
	
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
	 * Construtor WEB
	 */
	public Produto(String codigo, String nome, String descricao, String preco, String codigoMarca, String codigoSubGrupo, String codigoGrupo, int destaque) {
		this.nome = nome;
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.codigoMarca = codigoMarca;
		this.codigoSubGrupo = codigoSubGrupo;
		this.codigoGrupo = codigoGrupo;
		this.destaque = destaque;
		
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
			if (this.codigo.equals(p.codigo) || Integer.parseInt(this.codigo) == Integer.parseInt(p.codigo) 
					&& Integer.parseInt(this.codigoGrupo) == Integer.parseInt(p.codigoGrupo)
					&& Integer.parseInt(this.codigoMarca) == Integer.parseInt(p.codigoMarca) 
					&& Integer.parseInt(this.codigoSubGrupo) == Integer.parseInt(p.codigoSubGrupo)
					&& this.descricao.equals(p.descricao) && this.nome.equals(p.nome) && this.preco.equals(p.preco))
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
		return nome.replaceAll("[\\\\']", "\\\\'");
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao.replaceAll("[\\\\']", "\\\\'");
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
	
	public ArrayList<String> getImagens() {
		return imagens;
	}

	public void setImagens(ArrayList<String> imagens) {
		this.imagens = imagens;
		this.setContadorImagens(this.imagens.size());
	}

	public int getNumImagens() {
		//return imagens.size();
		return contadorImagens;
	}
	public void addImagem(String s) {
		imagens.add(s);
	}
	public void removeImagem(String s) {
		imagens.remove(s);
	}

	public int getDestaque() {
		return destaque;
	}

	public void setDestaque(int destaque) {
		this.destaque = destaque;
	}
	
	//setter e getter gregório imagem ,,|,,
	public int getContadorImagens(){
		return contadorImagens;
	}
	
	public void setContadorImagens(int contadorImangens){
		this.contadorImagens = contadorImangens;
	}
	
}
