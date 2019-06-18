package br.com.beblue.application.disc;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscService {

    void create(DiscDTO discDTO);

    void edit(DiscDTO discDTO);

    void delete(Long id);

    DiscDTO findById(Long id);

    Page<DiscDTO> findByGenreOrderByName(Genre genre, Pageable pageable);

}
