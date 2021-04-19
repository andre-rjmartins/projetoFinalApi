package br.com.cotiinformatica.validations;

import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.dtos.FuncionarioPutDTO;

public class FuncionarioPutValidation {
	
	public List<String> validate(FuncionarioPutDTO dto) {
		
		List<String> result = new ArrayList<String>();
		
		if(dto.getIdFuncionario() == null) {
			result.add("O Id do funcionário não foi encontrado.");
		}

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
		
		if(dto.getSalario() == null || dto.getSalario() == 0) {
			result.add("Informe o Salário do funcionário.");
		}
		
		return result;	
	}
	
}
