package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.domain.sale.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;


public interface SaleRepositorySpringData extends JpaRepository<Sale, Long> {

    @Query("FROM Sale s WHERE s.register >= :initialDate and s.register <= :finalDate")
    Page<Sale> searchByDate(Date initialDate, Date finalDate, Pageable pageable);

}
