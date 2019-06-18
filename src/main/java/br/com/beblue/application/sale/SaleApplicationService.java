package br.com.beblue.application.sale;

import br.com.beblue.application.sale.dto.SaleDTO;
import br.com.beblue.domain.sale.Sale;
import br.com.beblue.domain.sale.SaleRepository;
import br.com.beblue.shared.exceptions.SaleNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static br.com.beblue.application.sale.SaleFactory.createSale;

@Service
@Transactional
public class SaleApplicationService implements SaleService {

    private final SaleRepository saleRepository;

    public SaleApplicationService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void create(SaleDTO saleDTO) {
        saleRepository.save(createSale(saleDTO));
    }

    @Override
    public void edit(SaleDTO saleDTO) {
        saleRepository.edit(createSale(saleDTO));
    }

    @Override
    public void delete(SaleDTO saleDTO) {
        saleRepository.delete(createSale(saleDTO));
    }

    @Override
    public SaleDTO findById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        return sale.map(SaleFactory::createSaleDTO).orElseThrow(SaleNotFoundException::new);
    }
}
