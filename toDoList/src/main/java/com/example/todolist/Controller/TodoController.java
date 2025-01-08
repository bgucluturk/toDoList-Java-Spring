package com.example.todolist.Controller;

import com.example.todolist.Service.TodoService;
import com.example.todolist.dto.TodoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Bu sınıf bir REST controller'dır, yani HTTP isteklerine yanıt verir.
@RequestMapping("/api/todos")  // Tüm uç noktalar için temel yol, "/api/todos" olacak
public class TodoController {

    // TodoService sınıfını inject etmek için constructor kullanıyoruz
    private final TodoService todoService;

    // Constructor ile TodoService'i dependency injection (DI) aracılığıyla alıyoruz.
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Tüm Todo'ları listelemek için bir GET isteği.
     * /api/todos/getAll uç noktasına yapılacak GET isteği, tüm Todo'ları döndürür.
     * 
     * @return Tüm Todo'ların DTO listesi
     */
    @GetMapping("/getAll")  // GET isteği ile "/getAll" yolunu işaret eder
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        // TodoService'den tüm Todo'ları alır ve döndürür
        return ResponseEntity.ok(todoService.getAllTodos());  // HTTP 200 OK yanıtı ile döner
    }

    /**
     * ID'ye göre bir Todo'yu almak için bir GET isteği.
     * /api/todos/{id} yoluyla Todo'nun bilgileri alınabilir.
     * 
     * @param id ID'sine göre alınacak Todo'nun kimliği
     * @return Todo'nun DTO'su
     */
    @GetMapping("/{id}")  // GET isteği ile "/{id}" yolunu işaret eder
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        // TodoService'den belirli bir ID'ye ait Todo'yu alır ve döndürür
        return ResponseEntity.ok(todoService.getTodoById(id));  // HTTP 200 OK yanıtı ile döner
    }

    /**
     * Yeni bir Todo oluşturmak için POST isteği.
     * /api/todos/create yoluna yapılacak POST isteği, yeni bir Todo yaratır.
     * 
     * @param todoDTO Kullanıcıdan alınan Todo verileri
     * @return Oluşturulan Todo'nun DTO'su
     */
    @PostMapping("/create")  // POST isteği ile "/create" yolunu işaret eder
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO) {
        // TodoService'e DTO'yu göndererek yeni Todo oluşturulur
        return ResponseEntity.ok(todoService.createTodo(todoDTO));  // HTTP 200 OK yanıtı ile döner
    }

    /**
     * Varolan bir Todo'yu güncellemek için PUT isteği.
     * /api/todos/{id} yoluna yapılacak PUT isteği, varolan Todo'yu günceller.
     * 
     * @param id Todo'nun ID'si
     * @param todoDTO Güncellenecek Todo'nun verileri
     * @return Güncellenmiş Todo'nun DTO'su
     */
    @PutMapping("/{id}")  // PUT isteği ile "/{id}" yolunu işaret eder
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO) {
        // TodoService'den ilgili Todo'yu alır ve günceller
        return ResponseEntity.ok(todoService.updateTodo(id, todoDTO));  // HTTP 200 OK yanıtı ile döner
    }

    /**
     * Bir Todo'yu silmek için DELETE isteği.
     * /api/todos/{id} yoluna yapılacak DELETE isteği, Todo'yu siler.
     * 
     * @param id Silinecek Todo'nun ID'si
     * @return ResponseEntity<Void> ile HTTP 204 (No Content) yanıtı döner.
     */
    @DeleteMapping("/{id}")  // DELETE isteği ile "/{id}" yolunu işaret eder
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
        // TodoService'den ilgili Todo'yu bulur ve siler
        todoService.deleteTodoById(id);  // Silme işlemi gerçekleştirilir
        return ResponseEntity.noContent().build();  // HTTP 204 No Content yanıtı ile döner
    }
}