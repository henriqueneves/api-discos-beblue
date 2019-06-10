package br.com.beblue.domain.sale;

import java.util.Optional;

public interface SaleRepository {

    Sale save(Sale sale);

    Optional<Sale> findById(Long id);

}
