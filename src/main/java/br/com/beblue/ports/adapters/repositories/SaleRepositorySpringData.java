package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.domain.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleRepositorySpringData extends JpaRepository<Sale, Long> {

    Optional<Sale> findById(Long id);
}
