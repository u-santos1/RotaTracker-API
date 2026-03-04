package RotaTrackerAPI.RotaTrackerAPI.infra.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class RegraNegocioException extends DataIntegrityViolationException {
    public RegraNegocioException(String message) {
        super(message);
    }
}
