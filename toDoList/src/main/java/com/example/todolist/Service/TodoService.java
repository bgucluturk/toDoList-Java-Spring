package com.example.todolist.Service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todolist.Entity.Todo;
import com.example.todolist.Exception.ResourceNotFoundException;
import com.example.todolist.Repository.TodoRepository;
import com.example.todolist.dto.TodoDTO;

/**
 * TodoService sınıfı, Todo nesneleri üzerinde iş mantığını gerçekleştiren bir servis katmanıdır.
 * Bu sınıf, Todo'lar üzerinde CRUD (Create, Read, Update, Delete) işlemleri yapar ve DTO ile entity arasında dönüşüm yapar.
 */
@Service  // Bu sınıfın bir Spring Service sınıfı olduğunu belirtir
public class TodoService {

    private final TodoRepository todoRepository;  // TodoRepository ile etkileşim kurmak için TodoRepository'yi inject ediyoruz

    /**
     * Constructor metodu: TodoRepository'yi inject ederek TodoService sınıfını başlatır.
     * 
     * @param todoRepository TodoRepository sınıfı
     */
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Tüm Todo'ları listeleyen metod.
     * 
     * @return List<TodoDTO> Tüm Todo'ları DTO formatında döndüren liste
     */
    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll()  // Tüm Todo'ları veritabanından alır
                .stream()  // Listeyi stream'e dönüştürür
                .map(this::mapToDTO)  // Her Todo'yu DTO'ya dönüştürür
                .collect(Collectors.toList());  // Sonuçları liste olarak toplar
    }

    /**
     * Belirtilen ID'ye göre Todo nesnesini getirir.
     * 
     * @param id Aranan Todo'nun ID'si
     * @return TodoDTO İlgili Todo'yu DTO formatında döndüren metod
     * @throws ResourceNotFoundException Eğer Todo bulunamazsa, özel exception fırlatılır
     */
    public TodoDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)  // ID'ye göre Todo'yu veritabanından arar
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));  // Eğer bulunmazsa, özel exception fırlatılır
        return mapToDTO(todo);  // Bulunan Todo'yu DTO'ya dönüştürüp döndürür
    }

    /**
     * Yeni bir Todo oluşturur.
     * 
     * @param todoDTO Oluşturulacak Todo'nun DTO formatında verilmiş hali
     * @return TodoDTO Yeni oluşturulan Todo'yu DTO formatında döndüren metod
     */
    public TodoDTO createTodo(TodoDTO todoDTO) {
        Todo todo = mapToEntity(todoDTO);  // DTO'yu Todo entity'sine dönüştürür
        Todo savedTodo = todoRepository.save(todo);  // Veritabanına kaydeder
        return mapToDTO(savedTodo);  // Kaydedilen Todo'yu DTO'ya dönüştürüp döndürür
    }

    /**
     * Belirtilen ID'ye sahip Todo'yu günceller.
     * 
     * @param id Güncellenmesi gereken Todo'nun ID'si
     * @param todoDTO Yeni verilerle güncellenmiş Todo'nun DTO formatındaki hali
     * @return TodoDTO Güncellenmiş Todo'yu DTO formatında döndüren metod
     * @throws ResourceNotFoundException Eğer Todo bulunamazsa, özel exception fırlatılır
     */
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
        Todo todo = todoRepository.findById(id)  // ID'ye göre Todo'yu veritabanından arar
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));  // Eğer Todo bulunamazsa, exception fırlatılır

        // Todo'yu günceller
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);  // Güncellenen Todo'yu veritabanına kaydeder
        return mapToDTO(updatedTodo);  // Güncellenen Todo'yu DTO'ya dönüştürüp döndürür
    }

    /**
     * Belirtilen ID'ye sahip Todo'yu siler.
     * 
     * @param id Silinecek Todo'nun ID'si
     * @throws ResourceNotFoundException Eğer Todo bulunamazsa, özel exception fırlatılır
     */
    public void deleteTodoById(Long id) {
        Todo todo = todoRepository.findById(id)  // ID'ye göre Todo'yu veritabanından arar
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));  // Eğer Todo bulunamazsa, exception fırlatılır
        todoRepository.delete(todo);  // Todo'yu veritabanından siler
    }

    /**
     * Todo entity'sini DTO'ya dönüştürür.
     * 
     * @param todo Entity formatındaki Todo nesnesi
     * @return TodoDTO Dönüştürülmüş Todo nesnesi (DTO formatında)
     */
    private TodoDTO mapToDTO(Todo todo) {
        TodoDTO todoDTO = new TodoDTO();  // Yeni bir TodoDTO nesnesi oluşturur
        todoDTO.setTitle(todo.getTitle());  // Todo'nun başlığını DTO'ya kopyalar
        todoDTO.setDescription(todo.getDescription());  // Todo'nun açıklamasını DTO'ya kopyalar
        todoDTO.setCompleted(todo.isCompleted());  // Todo'nun tamamlanma durumunu DTO'ya kopyalar
        return todoDTO;  // Dönüştürülmüş DTO'yu döndürür
    }

    /**
     * TodoDTO'yu entity'ye dönüştürür.
     * 
     * @param todoDTO DTO formatındaki Todo nesnesi
     * @return Todo Dönüştürülmüş Todo nesnesi (Entity formatında)
     */
    private Todo mapToEntity(TodoDTO todoDTO) {
        Todo todo = new Todo();  // Yeni bir Todo entity'si oluşturur
        todo.setTitle(todoDTO.getTitle());  // DTO'dan başlık bilgisini entity'ye kopyalar
        todo.setDescription(todoDTO.getDescription());  // DTO'dan açıklama bilgisini entity'ye kopyalar
        todo.setCompleted(todoDTO.isCompleted());  // DTO'dan tamamlanma durumunu entity'ye kopyalar
        return todo;  // Dönüştürülmüş entity'yi döndürür
    }
}