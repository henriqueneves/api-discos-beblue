package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.domain.sale.Sale;
import br.com.beblue.domain.sale.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public class SaleRepositoryJpa  implements SaleRepository {

    private final SaleRepositorySpringData saleRepositorySpringData;

    public SaleRepositoryJpa(SaleRepositorySpringData saleRepositorySpringData){
        this.saleRepositorySpringData = saleRepositorySpringData;
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepositorySpringData.saveAndFlush(sale);
    }

    @Override
    public void edit(Sale sale) {
        saleRepositorySpringData.save(sale);
    }

    @Override
    public void delete(Sale sale) {
        saleRepositorySpringData.delete(sale);
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return saleRepositorySpringData.findById(id);
    }

    @Override
    public Page<Sale> searchByDate(Date initialDate, Date finalDate, Pageable pageable) {
        return saleRepositorySpringData.findByRegisterBetween(initialDate, finalDate, pageable);
    }
}
