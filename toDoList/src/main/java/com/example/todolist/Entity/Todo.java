package com.example.todolist.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Todo sınıfı, veritabanında "Todo" adlı tabloyu temsil eder.
 * Bu sınıf, Todo nesnelerinin özelliklerini ve veritabanıyla etkileşimini sağlar.
 */
@Entity  // Bu sınıfın bir JPA entity olduğunu belirtir (veritabanında bir tabloyu temsil eder)
public class Todo {

    @Id  // Bu alanın tablodaki birincil anahtar olduğunu belirtir
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID'nin otomatik olarak artan bir değere sahip olacağını belirtir
    private Long id;  // Todo'nun benzersiz kimliği

    private String title;  // Todo'nun başlığı (örneğin: "Ödev yap")

    private String description;  // Todo'nun açıklaması (örneğin: "Matematik ödevini yap")

    private boolean completed;  // Todo'nun tamamlanıp tamamlanmadığını belirten alan (true/false)

    /**
     * Todo'nun ID'sini döndüren getter metodu.
     * 
     * @return Todo'nun benzersiz kimliği
     */
    public Long getId() {
        return id;
    }

    /**
     * Todo'nun ID'sini ayarlamak için setter metodu.
     * 
     * @param id Todo'nun benzersiz kimliği
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Todo'nun başlığını döndüren getter metodu.
     * 
     * @return Todo'nun başlığı
     */
    public String getTitle() {
        return title;
    }

    /**
     * Todo'nun başlığını ayarlamak için setter metodu.
     * 
     * @param title Todo'nun başlığı
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Todo'nun tamamlanma durumunu döndüren getter metodu.
     * 
     * @return Todo'nun tamamlanma durumu (true/false)
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Todo'nun tamamlanma durumunu ayarlamak için setter metodu.
     * 
     * @param completed Todo'nun tamamlanma durumu (true/false)
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Todo'nun açıklamasını döndüren getter metodu.
     * 
     * @return Todo'nun açıklaması
     */
    public String getDescription() {
        return description;
    }

    /**
     * Todo'nun açıklamasını ayarlamak için setter metodu.
     * 
     * @param description Todo'nun açıklaması
     */
    public void setDescription(String description) {
        this.description = description;
    }
}

