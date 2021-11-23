package com.example.backendcrud.Exception;

/*
Esta clase controla los mensajes de excepción cuando, al realizar alguna de las operaciones
del CRUD no se encuentra el identificador del usuario.
 */

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
