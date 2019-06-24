package br.com.beblue.application.sale;

import br.com.beblue.application.sale.dto.CreateSaleDTO;
import br.com.beblue.application.sale.dto.SaleDTO;
import br.com.beblue.domain.sale.Sale;
import br.com.beblue.domain.sale.SaleRepository;
import br.com.beblue.ports.adapters.messaging.CashbackProducer;
import br.com.beblue.shared.exceptions.SaleNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static br.com.beblue.application.sale.SaleFactory.createSale;
import static br.com.beblue.application.sale.SaleFactory.createSaleDTO;

@Service
@Transactional
public class SaleApplicationService implements SaleService {


    private final SaleRepository saleRepository;

    private final CashbackProducer cashbackProducer;


    public SaleApplicationService(SaleRepository saleRepository, CashbackProducer cashbackProducer) {
        this.saleRepository = saleRepository;
        this.cashbackProducer = cashbackProducer;
    }

    public void create(CreateSaleDTO createSaleDTO) {
        Sale sale = createSale(createSaleDTO);
        saleRepository.save(sale);
        cashbackProducer.sendMessageSale(createSaleDTO(sale));
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
