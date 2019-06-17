package br.com.beblue.application.disc;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.Disc;

import java.util.Optional;

public class DiscFactory {

    public static DiscDTO createDiscDTO(Disc disc){
        return DiscDTO
                .builder()
                .id(disc.id())
                .name(disc.name())
                .genre(disc.genre())
                .price(disc.price())
                .build();
    }

    public static DiscDTO createDiscDTO(Optional<Disc> disc){
        return DiscDTO
                .builder()
                .id(disc.get().id())
                .name(disc.get().name())
                .genre(disc.get().genre())
                .price(disc.get().price())
                .build();
    }

    public static Disc createDisc(DiscDTO discDTO){
        return Disc
                .builder()
                .id(discDTO.id())
                .name(discDTO.name())
                .genre(discDTO.genre())
                .price(discDTO.price())
                .build();
    }
}
