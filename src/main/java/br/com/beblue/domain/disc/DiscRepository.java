package br.com.beblue.domain.disc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DiscRepository {

    void save(Disc disc);

    void edit(Disc disc);

    void delete(Long disc);

    Optional<Disc> findById(Long id);

    Page<Disc> findByGenre( Genre genre, Pageable pageable);

}
