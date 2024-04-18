package com.example.bookstore.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=7, message = "Автор не может быть меньше 7 символов!")
    private String author;

    @NotEmpty(message = "Название не может быть пустым!")
    private String title;

    @NotEmpty(message = "Издательноство не может быть пустым!")
    private String publishing;

    @NotEmpty(message = "Серия не может быть пустой!")
    private String seriya;

    @Min(message = "Год издания не может быть меньше 1452!", value = 1452)
    private int year;

    @Min(message = "Цена не может быть меньше 1!", value = 1)
    private float price;
}
