package br.com.romariodev.module.pd.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.romariodev.module.pd.entity.Equipe;

@Transactional
public interface EquipeRepository extends PagingAndSortingRepository<Equipe, Long> {
	Equipe findByIdEquipe(int idEquipe);

	@Modifying
	@Query(value = "update equipe set equipe.status = '1' "
			+ "where equipe.idEquipe = :id", nativeQuery = true)
	void ativarEquipe(@Param("id") int id);

	@Modifying
	@Query(value = "update equipe set equipe.status = '-1' "
			+ "where equipe.idEquipe = :id", nativeQuery = true)
	void inativarEquipe(@Param("id") int id);

	public Page<Equipe> findByStatus(int status, Pageable p);
	public List<Equipe>findByStatus(int status);

	Equipe findByLiderIdlider(int id);
}
