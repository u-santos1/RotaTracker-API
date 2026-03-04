package RotaTrackerAPI.RotaTrackerAPI.infra.exception;

import RotaTrackerAPI.RotaTrackerAPI.dtos.responses.ErroPadraoDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroPadraoDTO> tratarErroRegraNegocio(RecursoNaoEncontradoException ex){
        var erro = new ErroPadraoDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroPadraoDTO> tratarErroRegraNegocio(RegraNegocioException ex){
        var erro = new ErroPadraoDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadraoDTO> tratarErroGenerico(Exception e){
        var erro = new ErroPadraoDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno no servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

}
