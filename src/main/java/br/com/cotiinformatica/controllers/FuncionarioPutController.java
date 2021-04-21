package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.adapters.DTOEntityAdapter;
import br.com.cotiinformatica.dtos.FuncionarioPutDTO;
import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.services.FuncionarioService;
import br.com.cotiinformatica.validations.FuncionarioPutValidation;

@Controller
public class FuncionarioPutController {
	
	@Autowired
	private FuncionarioService service;
	
	@CrossOrigin
	@RequestMapping(value = "/api/funcionarios", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<List<String>> put(@RequestBody FuncionarioPutDTO dto) {
		
		List<String> result = new ArrayList<String>();
		
		try {
			
			FuncionarioPutValidation validation = new FuncionarioPutValidation();
			result = validation.validate(dto);
			
			if(result.size() > 0) {
				
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(result);
			}
			else {
				
				Funcionario funcionario = service.findById(dto.getIdFuncionario());
				
				if(funcionario == null) {
					result.add("Este funcionário não encontra-se cadastrado.");
					
					return ResponseEntity
							.status(HttpStatus.NOT_FOUND)
							.body(result);
				}
				else {
					
					Funcionario item = service.findByCpf(dto.getCpf());
					if(item != null && item.getIdFuncionario() != funcionario.getIdFuncionario()) {
						
						result.add("Este CPF já encontra-se cadastrado, tente outro.");
						
						return ResponseEntity
								.status(HttpStatus.FORBIDDEN)
								.body(result);
					}
					else{
						
						Funcionario funcionarioAtualizado = DTOEntityAdapter.getFuncionario(dto);
						
						funcionario.setNome(funcionarioAtualizado.getNome());
						funcionario.setCpf(funcionarioAtualizado.getCpf());
						funcionario.setSalario(funcionarioAtualizado.getSalario());
						
						service.createOrUpdate(funcionario);
						result.add("Funcionário atualizado com sucesso.");
						
						return ResponseEntity
								.status(HttpStatus.OK)
								.body(result);
					}
				}
			}
		}
		catch(Exception e) {
			result.add("Erro: " + e.getMessage());
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(result);
		}
	}
	
}
