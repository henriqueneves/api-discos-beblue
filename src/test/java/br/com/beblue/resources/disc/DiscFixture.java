package br.com.beblue.resources.disc;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.Disc;

import static br.com.beblue.resources.disc.DiscConstants.*;


public interface DiscFixture {

    static Disc disc(){
        return Disc.builder()
                .id(DISC_ID)
                .name(DISC_NAME)
                .genre(DISC_GENRE)
                .price(DISC_PRICE)
                .build();
    }

    static DiscDTO discDTO(){
        return DiscDTO.builder()
                .id(DISC_ID)
                .name(DISC_NAME)
                .genre(DISC_GENRE)
                .price(DISC_PRICE)
                .build();
    }

    static DiscDTO invalidDiscDTO(){
        return discDTO().genre(null);
    }

}
