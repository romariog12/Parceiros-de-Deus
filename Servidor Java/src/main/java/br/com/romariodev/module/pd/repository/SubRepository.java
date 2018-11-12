package br.com.romariodev.module.pd.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.romariodev.module.pd.entity.Sub;
@Transactional
public interface SubRepository extends CrudRepository<Sub, Long>{
	Sub findByIdSub(int id);
	@Modifying
	@Query(value="update sub set sub.status = -1 "
			+ "where sub.idSub = :id",
			nativeQuery = true)
	void inativarSub(
			@Param("id") int id
			);
	@Modifying
	@Query(value="update sub set sub.status = 1 "
			+ "where sub.idSub = :id",
			nativeQuery = true)
	void ativarSub(
			@Param("id") int id
			);
	Iterable<Sub> findByStatus(int i);
	Sub findByLiderIdlider(int id);


}
