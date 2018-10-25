package br.com.romariodev.module.pd.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.romariodev.module.pd.entity.Equipe;
@Transactional
public interface EquipeRepository extends CrudRepository<Equipe, Long>{
	Equipe findByIdEquipe(int idEquipe);
	@Modifying
	@Query(value="update equipe set equipe.status = 1 "
			+ "where equipe.idEquipe = :id",
			nativeQuery = true)
	void ativarEquipe(
			@Param("id") int id
			);
	@Modifying
	@Query(value="update equipe set equipe.status = -1 "
			+ "where equipe.idEquipe = :id",
			nativeQuery = true)
	void inativarEquipe(
			@Param("id") int id
			);

}
