package br.com.beblue.shared.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@EqualsAndHashCode(callSuper = false)
public class DefaultFilter extends PageRequest {

    @NotNull
    @PositiveOrZero
    public Integer page;

    @NotNull
    @Positive
    @Max(1000)
    public Integer size;

    public DefaultFilter() {
        this(0, 50);
        this.page = 0;
        this.size = 50;
    }

    public DefaultFilter(Integer page, Integer size) {
        super(page, size);
        this.page = page;
        this.size = size;
    }

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }

}
