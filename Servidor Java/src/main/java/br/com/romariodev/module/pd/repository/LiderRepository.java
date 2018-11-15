package br.com.romariodev.module.pd.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.romariodev.module.pd.entity.Lider;

@Transactional
public interface LiderRepository extends PagingAndSortingRepository<Lider, Long> {
	Lider findByIdlider(int id);

	@Modifying
	@Query(value = "update lider set lider.status = 1 "
			+ "where lider.idlider = :id", nativeQuery = true)
	void ativarLider(@Param("id") int id);

	@Modifying
	@Query(value = "update lider set lider.status = -1 "
			+ "where lider.idlider = :id", nativeQuery = true)
	void inativarLider(@Param("id") int id);

	Page<Lider> findByStatus(int s, Pageable page);
	List<Lider> findByStatus(int s);
}
