package br.com.bootcamp.casaDoCodigo.compartilhado.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidacaoErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<ErroFormulario> handleErroDeValidacao(MethodArgumentNotValidException e){
        return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro ->{
                   String campo = erro.getField();
                   String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
                   return new ErroFormulario(campo,mensagem);

                }).collect(Collectors.toList());
    }


    //Utilize o padrão nomeDoCampo_unico nas contraints para que handler
    //consiga recuperar o nome do campo
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErroFormulario handleErroDeCampoUnico(DataIntegrityViolationException e) {

        String causaCompleta = e.getMostSpecificCause().getMessage();
        Pattern pattern = Pattern.compile("(\\w+)_unico", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(causaCompleta);

        if (matcher.find()) {
            String campo = matcher.group(1);
            return new ErroFormulario(campo, "Já existe um registro com o valor informado");
        }
            return new ErroFormulario("Campo não identificado", "Já existe um registro com o valor informado");

    }
}
