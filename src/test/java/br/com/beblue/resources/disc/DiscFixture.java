package br.com.beblue.resources.disc;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.Disc;
import br.com.beblue.shared.utils.DefaultFilter;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.beblue.resources.disc.DiscConstants.*;
import static org.assertj.core.util.Lists.newArrayList;


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
        return discDTO().price(new BigDecimal(-2));
    }

    static DiscDTO emptyDiscDTO(){
        return new DiscDTO();
    }

    static Disc discWithIdOnly(){
        return Disc.builder()
                .id(DISC_ID)
                .build();
    }

    static Page<Disc> pageDisc(){
         return new PageImpl<>(newArrayList(disc()));
    }

    static DefaultFilter defaultFilter(){
        return new DefaultFilter();
    }

    static Optional<Disc> optionalDisc(){
        return Optional.of(disc());
    }

}
