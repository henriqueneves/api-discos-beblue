package br.com.beblue.application.disc;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.Disc;
import br.com.beblue.domain.disc.DiscRepository;
import br.com.beblue.shared.exceptions.DiscNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DiscApplicationService implements DiscService {

    private DiscRepository discRepository;

    public DiscApplicationService(DiscRepository discRepository) {
        this.discRepository = discRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public DiscDTO findById(Long id) {
        Optional<Disc> disc = discRepository.findById(id);
        return disc.map(DiscFactory::createDiscDTO).orElseThrow(DiscNotFoundException::new);
    }

}
