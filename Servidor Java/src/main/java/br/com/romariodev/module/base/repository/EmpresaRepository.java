package br.com.romariodev.module.base.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.romariodev.module.base.entity.Empresa;

/**
 * 
 * @author romario.portela <romariomacedo18@gmail.com>
 *
 */
@Transactional
public interface EmpresaRepository extends CrudRepository<Empresa, Long>{

	boolean findByCnpj(String cnpj);
	@Modifying
	@Query("UPDATE Empresa e SET cnpj = :cnpj, endereco = :endereco WHERE idEmpresa = :id")
	void updateEmpresa(@Param("id") int idEmpresa, @Param("cnpj") String cnpj, @Param("endereco") String endereco);
}
