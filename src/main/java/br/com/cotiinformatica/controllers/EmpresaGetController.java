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
import br.com.cotiinformatica.dtos.EmpresaGetDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.services.EmpresaService;

@Controller
public class EmpresaGetController {

	@Autowired
	private EmpresaService service;
	
	@CrossOrigin
	@RequestMapping(value = "/api/empresas", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<EmpresaGetDTO>> getAll(){
		
		List<EmpresaGetDTO> result = new ArrayList<EmpresaGetDTO>();
		
		try {
			
			List<Empresa> empresas = service.findAll();
			
			for(Empresa item : empresas) {
				result.add(EntityDTOAdapter.getEmpresaDTO(item));
			}
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(result);
		}
		catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(result);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/empresas/{idEmpresa}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<EmpresaGetDTO> getById(@PathVariable("idEmpresa") Integer idEmpresa) {
		
		EmpresaGetDTO dto = new EmpresaGetDTO();
		
		try {
			
			Empresa empresa = service.findById(idEmpresa);
			
			if(empresa != null) {
				
				dto = EntityDTOAdapter.getEmpresaDTO(empresa);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(dto);
			}
			else {
				
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(dto);
			}
			
		}
		catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(dto);
		}
	}
}
