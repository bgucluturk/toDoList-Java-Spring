package com.example.todolist.Repository;

import com.example.todolist.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Veritabanı ile ilgili özel bir sql yazılmak istenirse buradan işlemleri yapılmaktadır.
}

