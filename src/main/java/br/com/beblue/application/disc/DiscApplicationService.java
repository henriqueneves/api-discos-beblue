package br.com.beblue.application.disc;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.Disc;
import br.com.beblue.domain.disc.DiscRepository;
import br.com.beblue.domain.disc.Genre;
import br.com.beblue.shared.exceptions.DiscNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static br.com.beblue.application.disc.DiscFactory.*;

@Service
@Transactional
public class DiscApplicationService implements DiscService {

    private DiscRepository discRepository;

    public DiscApplicationService(DiscRepository discRepository) {
        this.discRepository = discRepository;
    }

    @Override
    public void create(DiscDTO discDTO) {
        discRepository.save(createDisc(discDTO));
    }

    @Override
    public void edit(DiscDTO discDTO) {
        discRepository.edit(createDisc(discDTO));
    }

    @Override
    public void delete(DiscDTO discDTO) {
        discRepository.delete(createDisc(discDTO));
    }


    @Override
    @Transactional(readOnly = true)
    public DiscDTO findById(Long id) {
        Optional<Disc> disc = discRepository.findById(id);
        return disc.map(DiscFactory::createDiscDTO).orElseThrow(DiscNotFoundException::new);
    }

    @Override
    public Page<DiscDTO> findByGenreOrderByName(Genre genre, Pageable pageable) {
        return discRepository.findByGenre(genre, pageable).map(DiscFactory::createDiscDTO);
    }
}
