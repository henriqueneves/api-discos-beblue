package br.com.beblue.ports.adapters.web;

import br.com.beblue.application.disc.DiscService;
import br.com.beblue.application.disc.dto.DiscDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/discs")
public class DiscController {

    private final DiscService discService;

    public DiscController(DiscService discService) {
        this.discService = discService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscDTO> findByDiscId(@PathVariable Long id) {
        DiscDTO discDTO = discService.findById(id);
        return ok(discDTO);
    }

}
