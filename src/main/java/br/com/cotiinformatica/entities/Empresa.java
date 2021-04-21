package br.com.cotiinformatica.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEmpresa")
	private Integer idEmpresa;
	
	@Column(name = "nomeFantasia", length = 150, nullable = false)
	private String nomeFantasia;
	
	@Column(name = "razaoSocial", length = 150, nullable = false, unique = true)
	private String razaoSocial;
	
	@Column(name = "cnpj", length = 25, nullable = false, unique = true)
	private String cnpj;
	
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<Funcionario> funcionarios;
	
	public Empresa() {
		// TODO Auto-generated constructor stub
	}

	public Empresa(Integer idEmpresa, String nomeFantasia, String razaoSocial, String cnpj) {
		super();
		this.idEmpresa = idEmpresa;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "Empresa [idEmpresa=" + idEmpresa + ", nomeFantasia=" + nomeFantasia + ", razaoSocial=" + razaoSocial
				+ ", cnpj=" + cnpj + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Empresa) {
			
			Empresa empresa = (Empresa) obj;
			
			return this.idEmpresa.equals(empresa.getIdEmpresa());
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.idEmpresa.hashCode();
	}
}
