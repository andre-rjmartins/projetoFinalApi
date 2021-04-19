package br.com.cotiinformatica.validations;

import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.dtos.EmpresaPutDTO;

public class EmpresaPutValidation {
	
	public List<String> validate(EmpresaPutDTO dto){
		
		List<String> result = new ArrayList<String>();
		
		if(dto.getIdEmpresa() == null) {
			result.add("O Id da empresa não foi encontrado.");
		}
		
		if(dto.getNomeFantasia() == null || dto.getNomeFantasia().length() == 0) {
			result.add("Informe o Nome Fantasia da empresa.");
		}
		
		if(dto.getNomeFantasia().length() < 3 || dto.getNomeFantasia().length() > 150) {
			result.add("O Nome Fantasia da empresa deve ter no mínimo 3 caracteres e no máximo 150 caracteres.");
		}
		
		if(dto.getRazaoSocial() == null || dto.getRazaoSocial().length() == 0) {
			result.add("Informe a Razão Social da empresa.");
		}
		
		if(dto.getRazaoSocial().length() < 10 || dto.getRazaoSocial().length() > 150) {
			result.add("A Razão Social da empresa deve ter no mínimo 10 caracteres e no máximo 150 caracteres.");
		}
		
		if(dto.getCnpj() == null || dto.getCnpj().length() == 0) {
			result.add("Informe o CNPJ da empresa.");
		}
		
		if(dto.getCnpj().length() != 18) {
			result.add("O CNPJ da empresa deve ter 18 caracteres.");
		}
		
		return result;
	}
	
}
