package com.scaler.productservicedecembermwf.ControllerAdvice;


import com.scaler.productservicedecembermwf.DTO.ArithmeticExceptionDto;
import com.scaler.productservicedecembermwf.DTO.ExceptionDTO;
import com.scaler.productservicedecembermwf.Exception.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException() {
        ArithmeticExceptionDto arithmeticExceptionDto = new ArithmeticExceptionDto();
        arithmeticExceptionDto.setMessage("Something has gone wrong");
        return new ResponseEntity<>(arithmeticExceptionDto, HttpStatus.OK);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundException() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotExistsException( ProductNotExistException exception) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(exception.getMessage());

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
