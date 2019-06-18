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

    private final DiscRepositorySpringData discRepositorySpringData;

    public DiscRepositoryJpa(DiscRepositorySpringData discRepositorySpringData) {
        this.discRepositorySpringData = discRepositorySpringData;
    }

    @Override
    public void save(Disc disc) {
        discRepositorySpringData.save(disc);
    }

    @Override
    public void edit(Disc disc) {
        discRepositorySpringData.save(disc);
    }

    @Override
    public void delete(Long id) {
        discRepositorySpringData.deleteById(id);
    }

    @Override
    public Optional<Disc> findById(Long id) {

        return discRepositorySpringData.findById(id);
    }

    @Override
    public Page<Disc> findByGenre(Genre genre, Pageable pageable) {
        return discRepositorySpringData.findByGenreOrderByName(genre, pageable);
    }
}
