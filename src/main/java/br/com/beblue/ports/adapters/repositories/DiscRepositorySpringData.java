package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.domain.disc.Disc;
import br.com.beblue.domain.disc.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscRepositorySpringData extends JpaRepository <Disc, Long> {

    Optional<Disc> findById(Long id);

    Page<Disc> findByGenreOrderByName(Genre genre, Pageable pageable);


}
