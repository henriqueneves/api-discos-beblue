package br.com.beblue.ports.adapters.web.error;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class FieldError implements Serializable {

    private static final long serialVersionUID = 3216546515984545646L;

    private String objectName;
    private String field;
    private String message;
}
