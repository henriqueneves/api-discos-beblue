package br.com.beblue.ports.adapters.web;

import br.com.beblue.application.sale.SaleApplicationService;
import br.com.beblue.application.sale.dto.CreateSaleDTO;
import br.com.beblue.application.sale.dto.SaleDTO;
import br.com.beblue.application.sale.dto.SearchSaleQueryDTO;
import br.com.beblue.shared.utils.DefaultFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleApplicationService saleService;

    public SaleController(SaleApplicationService saleApplicationService) {
        this.saleService = saleApplicationService;
    }

    @PostMapping
    public ResponseEntity<Void> createSale(@RequestBody @Valid CreateSaleDTO createSaleDTO) {
        saleService.create(createSaleDTO);
        return noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id){
        saleService.delete(SaleDTO.builder().id(id).build());
        return noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> getSaleDTO(@PathVariable Long id) {
        SaleDTO saleDTO = saleService.findById(id);
        return ok(saleDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SaleDTO>> searchSaleDTO(SearchSaleQueryDTO searchParameters, @PageableDefault DefaultFilter pageFilter) {
        Page<SaleDTO> page = saleService.searchByDate(searchParameters.getStart(), searchParameters.getEnd(), pageFilter.toPageable());
        return ok(page);
    }
}
