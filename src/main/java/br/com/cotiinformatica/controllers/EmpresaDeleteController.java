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

import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.services.EmpresaService;
import br.com.cotiinformatica.services.FuncionarioService;

@Controller
public class EmpresaDeleteController {

	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@CrossOrigin
	@RequestMapping(value = "/api/empresas/{idEmpresa}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<List<String>> delete(@PathVariable("idEmpresa") Integer idEmpresa) {
		
		List<String> result = new ArrayList<String>();
		
		try {
			
			Empresa empresa = empresaService.findById(idEmpresa);
			
			if(empresa == null) {
				result.add("Empresa não encontrada.");
				
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(result);
			}
			else {
				List<Funcionario> funcionarios = funcionarioService.findByIdEmpresa(idEmpresa);
				for(Funcionario item : funcionarios) {
					Funcionario funcionario = item;
					funcionario.setEmpresa(null);
					
					funcionarioService.createOrUpdate(funcionario);
				}
				
				empresaService.delete(empresa);
				
				result.add("Empresa excluída com sucesso.");
				
				return ResponseEntity
						.status(HttpStatus.OK)
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