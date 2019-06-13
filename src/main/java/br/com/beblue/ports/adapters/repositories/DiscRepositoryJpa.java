package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.domain.disc.Disc;
import br.com.beblue.domain.disc.DiscRepository;
import br.com.beblue.domain.disc.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DiscRepositoryJpa implements DiscRepository {

    DiscRepositorySpringData discRepositorySpringData;

    public DiscRepositoryJpa(DiscRepositorySpringData discRepositorySpringData) {
        this.discRepositorySpringData = discRepositorySpringData;
    }

    @Override
    public Disc save(Disc disc) {
        return discRepositorySpringData.save(disc);
    }

    @Override
    public Optional<Disc> findById(Long id) {
        return discRepositorySpringData.findById(id);
    }

    @Override
    public Page<Disc> findByGenre(Genre genre, Pageable pageable) {
        return null;
    }
}
