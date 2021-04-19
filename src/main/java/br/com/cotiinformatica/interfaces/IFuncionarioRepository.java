package br.com.cotiinformatica.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Funcionario;

public interface IFuncionarioRepository extends CrudRepository<Funcionario, Integer>{
	
	@Query("select f from Funcionario f where f.cpf =:param")
	Funcionario findByCpf(@Param("param") String cpf);
	
	@Query("select f from Funcionario f where f.empresa.idEmpresa =:param")
	List<Funcionario> findByIdEmpresa(@Param("param") Integer idEmpresa);
	
	/*
	@Query("update Funcionario f set f.empresa.idEmpresa = 1 where f.empresa.idEmpresa =:param")
	void updateIdEmpresa(@Param("param") Integer idEmpresa);
	*/
}
