package com.prj.agile.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class ClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if(response.status() == 404) {
            return new ClientNotFoundException("Recurso não encontrado para o método: " + methodKey);
        }
        return new Exception("Erro não mapeado: " + response.status());
    }
}
