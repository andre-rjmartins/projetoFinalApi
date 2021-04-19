package br.com.cotiinformatica.adapters;

import br.com.cotiinformatica.dtos.EmpresaGetDTO;
import br.com.cotiinformatica.dtos.FuncionarioGetDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.entities.Funcionario;

public class EntityDTOAdapter {
	
	public static FuncionarioGetDTO getFuncionarioDTO(Funcionario funcionario) {
		
		FuncionarioGetDTO dto = new FuncionarioGetDTO();
		
		dto.setIdFuncionario(funcionario.getIdFuncionario());
		dto.setNome(funcionario.getNome());
		dto.setCpf(funcionario.getCpf());
		dto.setDataAdmissao(funcionario.getDataAdmissao());
		dto.setSalario(funcionario.getSalario());
		dto.setEmpresa(funcionario.getEmpresa());
		
		return dto;
	}
	
	public static EmpresaGetDTO getEmpresaDTO(Empresa empresa) {
		
		EmpresaGetDTO dto = new EmpresaGetDTO();
		
		dto.setIdEmpresa(empresa.getIdEmpresa());
		dto.setNomeFantasia(empresa.getNomeFantasia());
		dto.setRazaoSocial(empresa.getRazaoSocial());
		dto.setCnpj(empresa.getCnpj());
		
		return dto;
	}
	
}
