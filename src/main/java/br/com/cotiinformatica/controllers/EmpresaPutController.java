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
import br.com.cotiinformatica.dtos.EmpresaPutDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.services.EmpresaService;
import br.com.cotiinformatica.validations.EmpresaPutValidation;

@Controller
public class EmpresaPutController {
	
	@Autowired
	private EmpresaService service;
	
	@CrossOrigin
	@RequestMapping(value = "/api/empresas", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<List<String>> put(@RequestBody EmpresaPutDTO dto) {
		
		List<String> result = new ArrayList<String>();
		
		try {
			
			EmpresaPutValidation validation = new EmpresaPutValidation();
			result = validation.validate(dto);
			
			if(result.size() > 0) {
				
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(result);
			}
			else {
				
				if(service.findById(dto.getIdEmpresa()) == null) {
					result.add("Esta empresa não encontra-se cadastrada.");
					
					return ResponseEntity
							.status(HttpStatus.NOT_FOUND)
							.body(result);
				}
				else {
					
					Empresa item1 = service.findByRazaoSocial(dto.getRazaoSocial());
					Empresa item2 = service.findByCnpj(dto.getCnpj());
					
					if(item1 != null && item1.getIdEmpresa() != dto.getIdEmpresa()) {
						result.add("Esta Razão Social já encontra-se cadastrada, tente outra.");
						
						return ResponseEntity
								.status(HttpStatus.FORBIDDEN)
								.body(result);
					}
					else if(item2 != null && item2.getIdEmpresa() != dto.getIdEmpresa()) {
						result.add("Este CNPJ já encontra-se cadastrado, tente outro.");
						
						return ResponseEntity
								.status(HttpStatus.FORBIDDEN)
								.body(result);
					}
					else {
						
						if(service.findById(dto.getIdEmpresa()) != null) {
							Empresa empresa = DTOEntityAdapter.getEmpresa(dto);
							
							service.createOrUpdate(empresa);
							result.add("Empresa atualizada com sucesso.");
							
							return ResponseEntity
									.status(HttpStatus.OK)
									.body(result);
						}
						else {
							result.add("Empresa não encontrada.");
							
							return ResponseEntity
									.status(HttpStatus.NOT_FOUND)
									.body(result);
						}
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
