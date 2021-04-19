package br.com.cotiinformatica.adapters;

import br.com.cotiinformatica.dtos.EmpresaPostDTO;
import br.com.cotiinformatica.dtos.EmpresaPutDTO;
import br.com.cotiinformatica.dtos.FuncionarioPostDTO;
import br.com.cotiinformatica.dtos.FuncionarioPutDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.utils.DateUtil;

public class DTOEntityAdapter {
	
	public static Empresa getEmpresa(EmpresaPostDTO dto) {
		
		Empresa empresa = new Empresa();
		
		empresa.setNomeFantasia(dto.getNomeFantasia());
		empresa.setRazaoSocial(dto.getRazaoSocial());
		empresa.setCnpj(dto.getCnpj());
		
		return empresa;
	}
	
	public static Empresa getEmpresa(EmpresaPutDTO dto) {
		
		Empresa empresa = new Empresa();
		
		empresa.setIdEmpresa(dto.getIdEmpresa());
		empresa.setNomeFantasia(dto.getNomeFantasia());
		empresa.setRazaoSocial(dto.getRazaoSocial());
		empresa.setCnpj(dto.getCnpj());
		
		return empresa;
	}
	
	public static Funcionario getFuncionario(FuncionarioPostDTO dto) {
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(dto.getNome());
		funcionario.setCpf(dto.getCpf());
		funcionario.setDataAdmissao(DateUtil.convertDate(dto.getDataAdmissao()));
		funcionario.setSalario(dto.getSalario());
		
		return funcionario;
	}
	
	public static Funcionario getFuncionario(FuncionarioPutDTO dto) {
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setIdFuncionario(dto.getIdFuncionario());
		funcionario.setNome(dto.getNome());
		funcionario.setCpf(dto.getCpf());
		funcionario.setSalario(dto.getSalario());
		
		return funcionario;
	}
	
}
