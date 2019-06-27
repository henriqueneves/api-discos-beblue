package br.com.beblue.ports.adapters.messaging;

import br.com.beblue.application.sale.SaleService;
import br.com.beblue.application.sale.dto.CalculatedCashbackDTO;
import br.com.beblue.application.sale.dto.SaleDTO;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import javax.validation.Valid;

import static br.com.beblue.domain.events.RestSource.CASHBACK_INPUT;

@Service
public class CashbackListener {

    private final SaleService saleService;

    public CashbackListener(SaleService saleService) {
        this.saleService = saleService;
    }

    @StreamListener(CASHBACK_INPUT)
    public void saveCashbackSale(@Payload @Valid CalculatedCashbackDTO calculatedCashbackDTO) {
        saleService.updateCashback(calculatedCashbackDTO);
    }

}
