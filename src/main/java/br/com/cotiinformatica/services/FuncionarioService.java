package br.com.cotiinformatica.services;

import java.util.List;
import java.util.Optional;

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
		
		Optional<Funcionario> funcionario = repository.findById(id);
		if(funcionario.isPresent()) {
			return funcionario.get();
		}
		
		return null;
	}
	
	public Funcionario findByCpf(String cpf) throws Exception{
		return repository.findByCpf(cpf);
	}
	
	public List<Funcionario> findByIdEmpresa(Integer idEmpresa) throws Exception {
		return repository.findByIdEmpresa(idEmpresa);
	}
	
}
