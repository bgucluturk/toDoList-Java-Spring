package com.example.todolist.Exception;


// Proje içerisinde alınan genel hataları string değer olarak gösterir.
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

