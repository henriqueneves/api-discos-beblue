package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.domain.disc.Disc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscRepositorySpringData extends JpaRepository <Disc, Long> {

    Optional<Disc> findById(Long id);

}
