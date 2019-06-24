package br.com.beblue.domain.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface RestSource {

    String CASHBACK_OUTPUT = "cashback-calculator";
    String CASHBACK_INPUT = "cashback-calculated";

    @Output(CASHBACK_OUTPUT)
    MessageChannel sendMessage();

    @Input(CASHBACK_INPUT)
    MessageChannel receiveMessage();

}
