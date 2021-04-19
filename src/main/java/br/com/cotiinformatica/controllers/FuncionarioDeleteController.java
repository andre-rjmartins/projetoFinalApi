package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.services.FuncionarioService;

@Controller
public class FuncionarioDeleteController {
	
	@Autowired
	private FuncionarioService service;
	
	@CrossOrigin
	@RequestMapping(value = "/api/funcionarios/{idFuncionario}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<List<String>> delete(@PathVariable("idFuncionario") Integer idFuncionario) {
		
		List<String> result = new ArrayList<String>();
		
		try {
			
			Funcionario funcionario = service.findById(idFuncionario);
			
			if(funcionario != null) {
				
				service.delete(funcionario);
				result.add("Funcionário excluído com sucesso.");
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(result);
			}
			else {
				result.add("Funcionário não encontrado.");
				
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(result);
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
