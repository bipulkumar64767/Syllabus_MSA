package com.tag.exceptions;

public class ClientNotActiveException extends RuntimeException {
   public ClientNotActiveException(String message) {
       super(message);
   }
}
