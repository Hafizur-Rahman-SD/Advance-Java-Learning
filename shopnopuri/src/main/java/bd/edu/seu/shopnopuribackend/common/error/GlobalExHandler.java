package bd.edu.seu.shopnopuribackend.common.error;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExHandler {

    // ---------- helpers ----------
    private ResponseEntity<ApiErr> res(HttpStatus st, String msg, HttpServletRequest req, Map<String, Object> details) {
        ApiErr body = ApiErr.builder()
                .ts(Instant.now())
                .status(st.value())
                .err(st.name())
                .msg(msg)
                .path(req.getRequestURI())
                .details(details)
                .build();
        return ResponseEntity.status(st).body(body);
    }

    // ---------- 404 ----------
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErr> notFound(EntityNotFoundException ex, HttpServletRequest req) {
        return res(HttpStatus.NOT_FOUND, ex.getMessage(), req, null);
    }

    // ---------- 409 (business rule conflicts like already saved) ----------
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiErr> conflict(IllegalStateException ex, HttpServletRequest req) {
        return res(HttpStatus.CONFLICT, ex.getMessage(), req, null);
    }

    // ---------- 400 (json parse / enum mismatch / invalid body) ----------
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErr> badJson(HttpMessageNotReadableException ex, HttpServletRequest req) {
        return res(HttpStatus.BAD_REQUEST, "Invalid request body / enum value", req, Map.of(
                "hint", "Check JSON format and enum values (UPPERCASE).",
                "cause", ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage()
        ));
    }

    // ---------- 400 (DTO validation: @Valid) ----------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErr> validation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, Object> fieldErrs = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe -> {
            // last write wins if same field repeats
            fieldErrs.put(fe.getField(), fe.getDefaultMessage());
        });
        return res(HttpStatus.BAD_REQUEST, "Validation failed", req, Map.of("fields", fieldErrs));
    }

    // ---------- 400 (param/path validation if you use @Validated) ----------
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErr> constraint(ConstraintViolationException ex, HttpServletRequest req) {
        Map<String, Object> errs = new LinkedHashMap<>();
        ex.getConstraintViolations().forEach(v -> errs.put(String.valueOf(v.getPropertyPath()), v.getMessage()));
        return res(HttpStatus.BAD_REQUEST, "Validation failed", req, Map.of("violations", errs));
    }

    // ---------- 401 ----------
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ApiErr> auth(AuthenticationException ex, HttpServletRequest req) {
        return res(HttpStatus.UNAUTHORIZED, "Unauthorized", req, null);
    }

    // ---------- 403 ----------
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErr> denied(AccessDeniedException ex, HttpServletRequest req) {
        return res(HttpStatus.FORBIDDEN, "Forbidden", req, null);
    }

    // ---------- fallback 500 ----------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErr> any(Exception ex, HttpServletRequest req) {
        log.error("Unhandled error: {} {}", req.getMethod(), req.getRequestURI(), ex);
        return res(HttpStatus.INTERNAL_SERVER_ERROR, "Server error", req, null);
    }
}
