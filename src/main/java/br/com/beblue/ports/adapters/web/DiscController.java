package br.com.beblue.ports.adapters.web;

import br.com.beblue.application.disc.DiscApplicationService;
import br.com.beblue.application.disc.dto.DiscDTO;
import org.springframework.data.domain.Pageable;
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

    @DeleteMapping
    public ResponseEntity<Void> deleteDisc(@RequestBody @Valid DiscDTO discDTO){
        discService.delete(discDTO);
        return noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<DiscDTO> getDiscDTO(@PathVariable Long id) {
        DiscDTO discDTO = discService.findById(id);
        return ok(discDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<DiscDTO> searchDiscDTO(@PageableDefault Pageable pageable) {

        return ok(null);

    }




}
