package br.com.romariodev.module.base.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.romariodev.module.base.entity.Instituicao;

@Transactional
public interface InstituicaoRepository extends CrudRepository<Instituicao, Long>{
	
	@Modifying
	@Query("UPDATE Instituicao i SET responsavelEstagio = :responsavelEstagio, endereco = :endereco WHERE idInstituicao = :id")
	void updateInstituicao(@Param("id") int idInstituicao, @Param("responsavelEstagio") String responsavelEstagio, @Param("endereco") String endereco);

}
