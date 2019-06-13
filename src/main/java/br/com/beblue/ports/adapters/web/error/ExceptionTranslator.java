package br.com.beblue.ports.adapters.web.error;


import br.com.beblue.shared.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationProblem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionTranslator implements ProblemHandling {

    private static final URI DEFAULT_TYPE = URI.create("http://beblue.com.br/problem");
    private static final String KEY_VIOLATIONS = "violations";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_PATH = "path";
    private static final String ERR_VALIDATION = "error.validation";
    private static final String ERR_NOT_FOUND = "error.notFound";

    @Override
    public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, NativeWebRequest request) {
        if (entity == null) {
            return entity;
        }
        Problem problem = entity.getBody();
        if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
            return entity;
        }
        ProblemBuilder builder = Problem.builder()
                .withType(DEFAULT_TYPE)
                .withStatus(problem.getStatus())
                .withTitle(problem.getTitle())
                .with(KEY_PATH, request.getNativeRequest(HttpServletRequest.class).getRequestURI());

        if (problem instanceof ConstraintViolationProblem) {
            builder
                    .with(KEY_VIOLATIONS, ((ConstraintViolationProblem) problem).getViolations())
                    .with(KEY_MESSAGE, ERR_VALIDATION);
        } else {
            builder
                    .withCause(((DefaultProblem) problem).getCause())
                    .withDetail(problem.getDetail())
                    .withInstance(problem.getInstance());
            problem.getParameters().forEach(builder::with);
            if (!problem.getParameters().containsKey(KEY_MESSAGE) && problem.getStatus() != null) {
                builder.with(KEY_MESSAGE, "error.http." + problem.getStatus().getStatusCode());
            }
        }
        return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
    }

    @Override
    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nonnull NativeWebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors().stream()
                .map(f -> new FieldError(f.getObjectName(), f.getField(), f.getCode()))
                .collect(Collectors.toList());

        Problem problem = Problem.builder()
                .withType(DEFAULT_TYPE)
                .withTitle("Method argument not valid")
                .withStatus(defaultConstraintViolationStatus())
                .with(KEY_MESSAGE, ERR_VALIDATION)
                .with("fieldErrors", fieldErrors)
                .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleBadRequestAlertException(NotFoundException ex, NativeWebRequest request) {
        Problem problem = Problem.builder()
                .withType(ex.getType())
                .withTitle(ex.getTitle())
                .withStatus(ex.getStatus())
                .with(KEY_MESSAGE, ERR_NOT_FOUND)
                .build();
        return create(ex, problem, request);
    }

}
