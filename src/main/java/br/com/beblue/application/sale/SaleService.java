package br.com.beblue.application.sale;

import br.com.beblue.application.sale.dto.SaleDTO;

public interface SaleService {

    void create(SaleDTO saleDTO);

    void edit(SaleDTO saleDTO);

    void delete(SaleDTO saleDTO);

    SaleDTO findById(Long id);


}
