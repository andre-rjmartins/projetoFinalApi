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
import br.com.cotiinformatica.dtos.EmpresaPostDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.services.EmpresaService;
import br.com.cotiinformatica.validations.EmpresaPostValidation;

@Controller
public class EmpresaPostController {
	
	@Autowired
	private EmpresaService service;
	
	@CrossOrigin
	@RequestMapping(value = "/api/empresas", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> post(@RequestBody EmpresaPostDTO dto) {
		
		List<String> result = new ArrayList<String>();
		
		try {
			
			EmpresaPostValidation validation = new EmpresaPostValidation();
			result = validation.validate(dto);
			
			if(result.size() > 0) {
				
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(result);
			}
			else {
				
				if(service.findByRazaoSocial(dto.getRazaoSocial()) != null) {
					result.add("Esta Razão Social já encontra-se cadastrada, tente outra.");
					
					return ResponseEntity
							.status(HttpStatus.FORBIDDEN)
							.body(result);
				}
				
				else if(service.findByCnpj(dto.getCnpj()) != null) {
					result.add("Este CNPJ já encontra-se cadastrado, tente outro.");
					
					return ResponseEntity
							.status(HttpStatus.FORBIDDEN)
							.body(result);
				}
				else {
					Empresa empresa = DTOEntityAdapter.getEmpresa(dto);
					
					service.createOrUpdate(empresa);
					result.add("Empresa cadastrada com sucesso.");
					
					return ResponseEntity
							.status(HttpStatus.CREATED)
							.body(result);
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
