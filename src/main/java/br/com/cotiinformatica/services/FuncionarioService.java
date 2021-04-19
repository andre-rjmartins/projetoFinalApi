package br.com.cotiinformatica.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.interfaces.IFuncionarioRepository;

@Service
@Transactional
public class FuncionarioService {
	
	@Autowired
	private IFuncionarioRepository repository;
	
	public void createOrUpdate(Funcionario funcionario) throws Exception{
		repository.save(funcionario);
	}
	
	public void delete(Funcionario funcionario) throws Exception{
		repository.delete(funcionario);
	}
	
	public List<Funcionario> findAll() throws Exception{
		return (List<Funcionario>) repository.findAll();
	}
	
	public Funcionario findById(Integer id) throws Exception{
		return repository.findById(id).get();
	}
	
	public Funcionario findByCpf(String cpf) throws Exception{
		return repository.findByCpf(cpf);
	}
	
	public List<Funcionario> findByIdEmpresa(Integer idEmpresa) throws Exception {
		return repository.findByIdEmpresa(idEmpresa);
	}
	
	/*
	public void deleteByIdEmpresa(Integer idEmpresa) throws Exception {
		repository.deleteByIdEmpresa(idEmpresa);
	}
	*/
	
	/*
	public void updateIdEmpresa(Integer idEmpresa) throws Exception {
		repository.updateIdEmpresa(idEmpresa);
	}
	*/
}
