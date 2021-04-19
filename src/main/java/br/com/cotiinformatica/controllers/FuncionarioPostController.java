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
import br.com.cotiinformatica.dtos.FuncionarioPostDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.services.EmpresaService;
import br.com.cotiinformatica.services.FuncionarioService;
import br.com.cotiinformatica.validations.FuncionarioPostValidation;

@Controller
public class FuncionarioPostController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@CrossOrigin
	@RequestMapping(value = "/api/funcionarios", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> post(@RequestBody FuncionarioPostDTO dto){
		
		List<String> result = new ArrayList<String>();
		
		try {
			
			FuncionarioPostValidation validation = new FuncionarioPostValidation();
			result = validation.validate(dto);
			
			if(result.size() > 0) {
				
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(result);
			}
			else {
				
				if(funcionarioService.findByCpf(dto.getCpf()) != null) {
					result.add("Este CPF já encontra-se cadastrado, tente outro.");
					
					return ResponseEntity
							.status(HttpStatus.FORBIDDEN)
							.body(result);
				}
				else{
					
					Empresa empresa = empresaService.findByCnpj(dto.getCnpjEmpresa());
					
					if(empresa == null) {
						result.add("O CNPJ informado não encontra-se cadastrado, tente outro.");
						
						return ResponseEntity
								.status(HttpStatus.NOT_FOUND)
								.body(result);
					}
					else {
						
						Funcionario funcionario = DTOEntityAdapter.getFuncionario(dto);
						funcionario.setEmpresa(empresa);
						
						funcionarioService.createOrUpdate(funcionario);
						result.add("Funcionário cadastrado com sucesso.");
						
						return ResponseEntity
								.status(HttpStatus.CREATED)
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
