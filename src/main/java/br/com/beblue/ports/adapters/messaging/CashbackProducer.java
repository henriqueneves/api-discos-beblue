package br.com.beblue.ports.adapters.messaging;

import br.com.beblue.application.sale.dto.SaleDTO;
import br.com.beblue.domain.events.RestSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Service
public class CashbackProducer {

    @Autowired
    private RestSource restSource;

    public boolean sendMessageSale(SaleDTO saleDTO){
        Message<SaleDTO> message = MessageBuilder.withPayload(saleDTO).build();
        return restSource.sendMessage().send(message);
    }

}