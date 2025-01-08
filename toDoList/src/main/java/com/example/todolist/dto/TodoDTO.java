package com.example.todolist.dto;

public class TodoDTO {
    private String title;  // Todo başlığını tutacak alan
    private String description;  // Todo açıklamasını tutacak alan
    private boolean completed;  // Todo'nun tamamlanıp tamamlanmadığını belirten alan

    /**
     * title alanını döndüren getter metodu.
     * 
     * @return title başlığı
     */
    public String getTitle() {
        return title;
    }

    /**
     * title alanını ayarlamak için setter metodu.
     * 
     * @param title başlık değeri
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * description alanını döndüren getter metodu.
     * 
     * @return description açıklaması
     */
    public String getDescription() {
        return description;
    }

    /**
     * description alanını ayarlamak için setter metodu.
     * 
     * @param description açıklama değeri
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * completed alanını döndüren getter metodu.
     * 
     * @return completed Todo'nun tamamlanma durumunu gösterir
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * completed alanını ayarlamak için setter metodu.
     * 
     * @param completed Todo'nun tamamlanma durumu
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}