package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.domain.sale.Sale;
import br.com.beblue.domain.sale.SaleRepository;

import java.util.Optional;

public class SaleRepositoryJpa  implements SaleRepository {

    @Override
    public Sale save(Sale sale) {
        return null;
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return Optional.empty();
    }
}
