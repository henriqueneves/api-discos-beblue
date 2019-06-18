package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.domain.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SaleRepositorySpringData extends JpaRepository<Sale, Long> {

}
