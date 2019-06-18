package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.domain.disc.Disc;
import br.com.beblue.domain.disc.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscRepositorySpringData extends JpaRepository <Disc, Long> {

    Page<Disc> findByGenreOrderByName(Genre genre, Pageable pageable);


}
