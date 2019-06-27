package br.com.beblue.application.sale;

import br.com.beblue.application.sale.dto.CalculatedCashbackDTO;
import br.com.beblue.application.sale.dto.CreateSaleDTO;
import br.com.beblue.application.sale.dto.SaleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface SaleService {

    void create(CreateSaleDTO createSaleDTO);

    void edit(SaleDTO saleDTO);

    void delete(SaleDTO saleDTO);

    SaleDTO findById(Long id);

    Page<SaleDTO> searchByDate(Date initialDate, Date finalDate, Pageable pageable);

    void updateCashback(CalculatedCashbackDTO calculatedCashbackDTO);


}
