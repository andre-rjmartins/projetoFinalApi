package br.com.cotiinformatica.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFuncionario")
	private Integer idFuncionario;
	
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;
	
	@Column(name = "cpf", length = 15, nullable = false, unique = true)
	private String cpf;
	
	@Column(name = "dataAdmissao", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;
	
	@Column(name = "salario", nullable = false)
	private Double salario;
	
	@ManyToOne
	@JoinColumn(name = "idEmpresa", nullable = false)
	private Empresa empresa;
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public Funcionario(Integer idFuncionario, String nome, String cpf, Date dataAdmissao, Double salario,
			Empresa empresa) {
		super();
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.cpf = cpf;
		this.dataAdmissao = dataAdmissao;
		this.salario = salario;
		this.empresa = empresa;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Funcionario [idFuncionario=" + idFuncionario + ", nome=" + nome + ", cpf=" + cpf + ", dataAdmissao="
				+ dataAdmissao + ", salario=" + salario + ", empresa=" + empresa + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Funcionario) {
			
			Funcionario funcionario = (Funcionario) obj;
			
			return this.idFuncionario.equals(funcionario.getIdFuncionario());
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.idFuncionario.hashCode();
	}
}
