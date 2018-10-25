package br.com.romariodev.module.base.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.romariodev.module.base.entity.Contrato;
@Transactional
public interface ContratoRepository extends JpaRepository<Contrato, Long>{
public List<Contrato> findByFim(LocalDate recisao);

/*@Query("SELECT e FROM Estagio e "
		+ "INNER JOIN Contrato c "
		+ "ON e.idEstagio = c.estagio_idEstagio "
		+ "WHERE c.fim < :dataAtual "
		+ "AND Recisao = null "
		+ "GROUP BY e.idEstagio "
		+ "ORDER BY c.fim DESC")
public List<Contrato> findByFim(@Param("dataAtual") LocalDate dataAtual);
*/
}
