package br.com.beblue.shared.exceptions;

import lombok.Getter;
import org.zalando.problem.Status;

import java.net.URI;

@Getter
public class NotFoundException extends RuntimeException {

    private final static URI DEFAULT_TYPE = URI.create("about:blank");

    private final URI type;
    private final String title;
    private final Status status;

    public NotFoundException(String title) {
        this(DEFAULT_TYPE, title, Status.NOT_FOUND);
    }

    public NotFoundException(URI type, String title, Status status) {
        super(title);
        this.type = type;
        this.title = title;
        this.status = status;
    }

}
