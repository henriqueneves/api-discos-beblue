package br.com.beblue.domain.disc;

import java.util.Optional;

public interface DiscRepository {

    Disc save(Disc disc);

    Optional<Disc> findById(Long id);

}
