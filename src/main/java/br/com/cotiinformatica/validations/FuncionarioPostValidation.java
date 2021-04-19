package br.com.cotiinformatica.validations;

import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.dtos.FuncionarioPostDTO;

public class FuncionarioPostValidation {
	
	public List<String> validate(FuncionarioPostDTO dto){
		
		List<String> result = new ArrayList<String>();
		
		if(dto.getNome() == null || dto.getNome().length() == 0) {
			result.add("Informe o Nome do funcionário.");
		}
		
		if(dto.getNome().length() < 3 || dto.getNome().length() > 150) {
			result.add("O Nome do funcionário deve conter no mínimo 3 caracteres e no máximo 150 caracteres.");
		}
		
		if(dto.getCpf() == null || dto.getCpf().length() == 0) {
			result.add("Informe o CPF do funcionário.");
		}
		
		if(dto.getCpf().length() != 14) {
			result.add("O CPF do funcionário deve conter 14 caracteres.");
		}
		
		if(dto.getDataAdmissao() == null) {
			result.add("Informe a Data de Admissão do funcionário.");
		}
		
		if(dto.getSalario() == null || dto.getSalario() == 0) {
			result.add("Informe o Salário do funcionário.");
		}
		
		if(dto.getCnpjEmpresa() == null || dto.getCnpjEmpresa().length() == 0) {
			result.add("Informe o CNPJ da empresa.");
		}
		
		if(dto.getCnpjEmpresa().length() != 18) {
			result.add("O CNPJ da empresa deve conter 18 caracteres.");
		}
		
		return result;
	}
	
}
