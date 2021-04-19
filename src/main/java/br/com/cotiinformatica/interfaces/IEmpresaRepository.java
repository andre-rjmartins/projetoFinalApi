package br.com.cotiinformatica.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Empresa;

public interface IEmpresaRepository extends CrudRepository<Empresa, Integer>{
	
	@Query("select e from Empresa e where e.razaoSocial =:param")
	Empresa findByRazaoSocial(@Param("param") String razaoSocial);
	
	@Query("select e from Empresa e where e.cnpj =:param")
	Empresa findByCnpj(@Param("param") String cnpj);
}
