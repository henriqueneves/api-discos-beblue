package br.com.beblue.application.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Builder)
public class SearchSaleQueryDTO {

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date start = new Date(0);

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date end = new Date(Long.MAX_VALUE);
}
