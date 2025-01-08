package com.example.todolist.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration  // Bu sınıfın Spring'in konfigürasyon sınıfı olduğunu belirtir
public class SwaggerConfig {

    /**
     * Swagger/OpenAPI konfigürasyonu için custom bir OpenAPI nesnesi oluşturur.
     * Burada API'yi tanımlamak için başlık, versiyon ve açıklama eklenmiştir.
     * 
     * @return OpenAPI nesnesi
     */
    @Bean  // Spring, bu metodu bir Bean olarak tanıyacak ve DI (Dependency Injection) yapacaktır.
    public OpenAPI customOpenAPI() {
        // OpenAPI nesnesini oluşturuyoruz ve API bilgilerini ekliyoruz.
        return new OpenAPI()
                .info(new Info()  // API'yi tanımlayan bilgiler
                        .title("To-Do List API")  // API'nin başlığı
                        .version("1.0.0")  // API'nin versiyonu
                        .description("This is a RESTful API for managing To-Do tasks."));  // API'nin kısa açıklaması
    }
}

// http://localhost:4444/swagger-ui/index.html bu link üzerinden swagger arayüzüne ulaşabilirsin.
