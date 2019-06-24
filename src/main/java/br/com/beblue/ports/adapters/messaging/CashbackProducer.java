package br.com.beblue.ports.adapters.messaging;

import br.com.beblue.domain.events.RestSource;
import br.com.beblue.domain.sale.Sale;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Component
public class CashbackProducer {

    public boolean sendMessageSale(Sale sale, RestSource restSource){
        Message<Sale> message = MessageBuilder.withPayload(sale).build();
        boolean success = restSource.sendMessage().send(message);
        return success;
    }

}