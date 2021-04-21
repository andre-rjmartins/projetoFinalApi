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

import br.com.cotiinformatica.adapters.EntityDTOAdapter;
import br.com.cotiinformatica.dtos.FuncionarioGetDTO;
import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.services.FuncionarioService;

@Controller
public class FuncionarioGetController {
	
	@Autowired
	private FuncionarioService service;
	
	@CrossOrigin
	@RequestMapping(value = "/api/funcionarios", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<FuncionarioGetDTO>> get() {
		
		List<FuncionarioGetDTO> result = new ArrayList<FuncionarioGetDTO>();
		
		try {
			
			List<Funcionario> funcionarios = service.findAll();
			
			for(Funcionario item : funcionarios) {
				result.add(EntityDTOAdapter.getFuncionarioDTO(item));
			}
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(result);
		}
		catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/funcionarios/{idFuncionario}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FuncionarioGetDTO> getById(@PathVariable("idFuncionario") Integer idFuncionario) {
		
		FuncionarioGetDTO dto = new FuncionarioGetDTO();
		
		try {
			
			Funcionario funcionario = service.findById(idFuncionario);
			
			if(funcionario != null) {
				
				dto = EntityDTOAdapter.getFuncionarioDTO(funcionario);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(dto);
			}
			else {
				
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(null);
			}
			
		}
		catch(Exception e) {
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
		
	}
}
