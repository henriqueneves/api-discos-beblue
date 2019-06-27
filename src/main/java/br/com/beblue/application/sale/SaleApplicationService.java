package br.com.beblue.application.sale;

import br.com.beblue.application.sale.dto.CalculatedCashbackDTO;
import br.com.beblue.application.sale.dto.CreateSaleDTO;
import br.com.beblue.application.sale.dto.DiscsCalculatedCashbackDTO;
import br.com.beblue.application.sale.dto.SaleDTO;
import br.com.beblue.domain.disc.Disc;
import br.com.beblue.domain.disc.DiscRepository;
import br.com.beblue.domain.sale.DiscSale;
import br.com.beblue.domain.sale.Sale;
import br.com.beblue.domain.sale.SaleRepository;
import br.com.beblue.ports.adapters.messaging.CashbackProducer;
import br.com.beblue.shared.exceptions.MapErrorException;
import br.com.beblue.shared.exceptions.SaleNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.beblue.application.sale.SaleFactory.*;

@Service
@Transactional
public class SaleApplicationService implements SaleService {

    private final SaleRepository saleRepository;
    private final DiscRepository discRepository;
    private final CashbackProducer cashbackProducer;

    public SaleApplicationService(
            SaleRepository saleRepository,
            CashbackProducer cashbackProducer,
            DiscRepository discRepository
    ) {
        this.saleRepository = saleRepository;
        this.cashbackProducer = cashbackProducer;
        this.discRepository = discRepository;
    }

    @Override
    public void create(CreateSaleDTO createSaleDTO) {
        Sale sale = createSale(createSaleDTO);
        sale.discSales(loadDiscSales(sale));
        sale.calculeValueTotal();
        sale.registerNow();
        sale = saleRepository.save(sale);

        sale = saleRepository.findById(sale.id()).get();
        cashbackProducer.sendMessageSale(
                createSaleDTO(sale)
        );
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

    @Override
    public Page<SaleDTO> searchByDate(Date initialDate, Date finalDate, Pageable pageable) {
        return saleRepository.searchByDate(initialDate, finalDate, pageable).map(SaleFactory::createSaleDTO);
    }

    @Override
    public void updateCashback(CalculatedCashbackDTO calculatedCashbackDTO){
        Optional<Sale> optionalSale = saleRepository.findById(calculatedCashbackDTO.idSale());
        if(optionalSale.isPresent()){
            Sale sale = setCashbackValues(optionalSale.get(), calculatedCashbackDTO);
            saleRepository.edit(sale);
        }
    }

    private List<DiscSale> loadDiscSales(Sale sale){
        List<DiscSale> returnDiscSales = new ArrayList<>();
        for(DiscSale discSale : sale.discSales()){
            Disc disc = discRepository.findById(discSale.id()).get();
            returnDiscSales.add(
                    DiscSale.builder()
                            .disc(disc)
                            .saleValue(disc.price())
                            .sale(sale)
                            .build()
            );
        }
        return returnDiscSales;
    }

    public static Sale setCashbackValues(Sale sale, CalculatedCashbackDTO calculatedCashbackDTO){
        for(DiscSale discSale : sale.discSales()){
            DiscsCalculatedCashbackDTO discCalculated = getDiscsCalculatedCashbackDTO(discSale, calculatedCashbackDTO.discs());
            discSale.cashbackValue(discCalculated.cashbackValue());
        }
        sale.cashbackTotal(calculatedCashbackDTO.cashbackTotal());
        return sale;
    }

    private static DiscsCalculatedCashbackDTO getDiscsCalculatedCashbackDTO(DiscSale discSale, List<DiscsCalculatedCashbackDTO> discsCalculated){
        Optional<DiscsCalculatedCashbackDTO> discsCalculatedCashbackDTO = Optional.empty();
        for(DiscsCalculatedCashbackDTO discCalculated : discsCalculated){
            if(discCalculated.idDiscSale().equals(discSale.id())){
                discsCalculatedCashbackDTO = Optional.of(discCalculated);
            }
        }
        return discsCalculatedCashbackDTO.orElseThrow(MapErrorException::new);
    }

}
