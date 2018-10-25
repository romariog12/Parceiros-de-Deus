package br.com.romariodev.module.pd.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.romariodev.module.pd.entity.Pd;

@Transactional
public interface PdRepository extends CrudRepository<Pd, Long>{

	@Modifying
	@Query(value="update pd set pd.individual = :individual, pd.celula = :celula "
			+ "where pd.semana = :semana and month(pd.data) = :mes and year(pd.data) = :ano and pd.lider_idlider = :id",
			nativeQuery = true)
	void pdSemanal(
			@Param("semana") int semana,
			@Param("individual") float individual,
			@Param("celula") float celula,
			@Param("id") long id,
			@Param("mes") int mes,
			@Param("ano") int ano
			);
	
	@Query(value="select * from pd where (pd.semana = :semana and month(pd.data) = :mes and year(pd.data) = :ano) order by (pd.celula + pd.individual) desc",
			nativeQuery = true)
	List<Pd> listaPd(
			@Param("semana") int semana,
			@Param("mes") int mes,
			@Param("ano") int ano
			);

	@Query(value="select * from pd where (pd.lider_idLider = :idLider and pd.semana = :semana and month(pd.data) = :mes and year(pd.data) = :ano) order by (pd.celula + pd.individual) desc",
			nativeQuery = true)
	Pd procurarPdPorIdLider(
			@Param("idLider") int idLider,
			@Param("semana") int semana,
			@Param("mes") int mes,
			@Param("ano") int ano
			);
	@Query(value="select sum(celula) as celula, sum(individual)as individual, idpd, data, semana, lider_idLider, equipe_idEquipe  from pd where (month(pd.data) = :mes and year(pd.data) = :ano) group by lider_idLider  order by (sum(celula) + sum(individual)) desc",
			nativeQuery = true)
	List<Pd> listaPdPorMes(
			@Param("mes") int mes,
			@Param("ano") int ano
			);
	@Query(value="select sum(celula) as celula, sum(individual)as individual, idpd, data, semana, lider_idLider, equipe_idEquipe  from pd where (month(pd.data) = :mes and year(pd.data) = :ano) group by equipe_idEquipe order by (sum(celula) + sum(individual)) desc",
			nativeQuery = true)
	List<Pd> listaPdPorMesEquipe(
			@Param("mes") int mes,
			@Param("ano") int ano
			);
	@Query(value="select sum(individual)as individual,celula, idpd, data, semana, lider_idLider, equipe_idEquipe  from pd where (month(pd.data) = :mes and year(pd.data) = :ano) group by lider_idLider  order by (sum(individual)) desc",
			nativeQuery = true)
	List<Pd> listaPdIndividualPorMes(
			@Param("mes") int mes,
			@Param("ano") int ano
			);
	@Query(value="select * from pd where (pd.idlider = :idlider and month(pd.data) = :mes and year(pd.data) = :ano) order by (pd.celula + pd.individual) desc",
			nativeQuery = true)
	List<Pd> listaPdPorLider(
			@Param("idlider") int idlider,
			@Param("mes") int mes,
			@Param("ano") int ano
			);
	@Query(value="select * from pd where (pd.equipe_idEquipe = :idEquipe and month(pd.data) = :mes and year(pd.data) = :ano) order by (pd.celula + pd.individual) desc",
			nativeQuery = true)
	List<Pd> listaPdPorIdEquipe(
			@Param("idEquipe") int idEquipe,
			@Param("mes") int mes,
			@Param("ano") int ano
			);
	@Query(value="select sum(celula) as celula, sum(individual)as individual, idpd, data, semana, lider_idLider, equipe_idEquipe  from pd where (pd.semana = :semana and month(pd.data) = :mes and year(pd.data) = :ano) group by equipe_idEquipe order by (sum(celula) + sum(individual)) desc",
			nativeQuery = true)
	List<Pd> listaPdPorEquipe(
			@Param("semana") int semana,
			@Param("mes") int mes,
			@Param("ano") int ano
			);
	Pd findByIdpd(int id);
	List<Pd> findByEquipeIdEquipe(int id);
	List<Pd> findByLiderIdlider(int id);
	
}
