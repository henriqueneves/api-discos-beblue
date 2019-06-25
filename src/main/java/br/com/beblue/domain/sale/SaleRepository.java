package br.com.beblue.domain.sale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SaleRepository {

    Sale save(Sale sale);

    void edit(Sale sale);

    void delete(Sale sale);

    Optional<Sale> findById(Long id);

    Page<Sale> findByDate( Pageable pageable);

}
