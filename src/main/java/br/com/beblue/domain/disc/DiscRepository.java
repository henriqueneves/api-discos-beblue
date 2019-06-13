package br.com.beblue.domain.disc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DiscRepository {

    Disc save(Disc disc);

    Optional<Disc> findById(Long id);

    Page<Disc> findByGenre( Genre genre, Pageable pageable);

}
