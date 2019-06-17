package br.com.beblue.ports.adapters.web;

import br.com.beblue.application.disc.DiscApplicationService;
import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.Genre;
import br.com.beblue.shared.utils.DefaultFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/discs")
public class DiscController {

    private final DiscApplicationService discService;

    public DiscController(DiscApplicationService discService) {
        this.discService = discService;
    }

    @PostMapping
    public ResponseEntity<Void> createDisc(@RequestBody @Valid DiscDTO discDTO) {
        discService.create(discDTO);
        return noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> editDisc(@RequestBody @Valid DiscDTO discDTO) {
        discService.edit(discDTO);
        return noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisc(@PathVariable Long id){
        discService.delete(DiscDTO.builder().id(id).build());
        return noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<DiscDTO> getDiscDTO(@PathVariable Long id) {
        DiscDTO discDTO = discService.findById(id);
        return ok(discDTO);
    }

    @GetMapping("/search/{genre}")
    public ResponseEntity<Page<DiscDTO>> searchDiscDTO(@PathVariable Genre genre, @PageableDefault DefaultFilter pageFilter) {
        Page<DiscDTO> page = discService.findByGenreOrderByName(genre, pageFilter.toPageable());
        return ok(page);

    }




}
