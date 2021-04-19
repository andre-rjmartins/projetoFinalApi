package br.com.cotiinformatica.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.interfaces.IEmpresaRepository;

@Service
@Transactional
public class EmpresaService {
	
	@Autowired
	private IEmpresaRepository repository;
	
	public void createOrUpdate(Empresa empresa) throws Exception{
		repository.save(empresa);
	}
	
	public void delete(Empresa empresa) throws Exception{
		repository.delete(empresa);
	}
	
	public List<Empresa> findAll() throws Exception{
		return (List<Empresa>) repository.findAll();
	}
	
	public Empresa findById(Integer id) throws Exception{
		return repository.findById(id).get();
	}
	
	public Empresa findByRazaoSocial(String razaoSocial) throws Exception{
		return repository.findByRazaoSocial(razaoSocial);
	}
	
	public Empresa findByCnpj(String cnpj) throws Exception{
		return repository.findByCnpj(cnpj);
	}
}
