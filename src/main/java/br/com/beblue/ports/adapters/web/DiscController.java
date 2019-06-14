package br.com.beblue.ports.adapters.web;

import br.com.beblue.application.disc.DiscService;
import br.com.beblue.application.disc.dto.DiscDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/discs")
public class DiscController {

    private final DiscService discService;

    public DiscController(DiscService discService) {
        this.discService = discService;
    }

    @PostMapping
    public ResponseEntity<Void> createDisc(@RequestBody @Valid DiscDTO discDTO) {
        discService.create(discDTO);
        return noContent().build();
    }

    @PutMapping("/edit")
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
    public ResponseEntity<DiscDTO> searchDiscDTO(@PathVariable Long id) {
        return ok(null);
    }




}
