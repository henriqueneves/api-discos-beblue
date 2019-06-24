package br.com.beblue.ports.adapters.messaging;

import br.com.beblue.application.sale.dto.SaleDTO;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static br.com.beblue.domain.events.RestSource.CASHBACK_INPUT;

@Service
public class CashbackListener {

    @StreamListener(CASHBACK_INPUT)
    public void saveCashbackSale(@Payload SaleDTO saleDTO) {
        System.out.println("Aqui: " + saleDTO.id());
    }

}
