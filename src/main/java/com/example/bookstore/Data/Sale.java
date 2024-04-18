package com.example.bookstore.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, message = "Фамилия продавца не может быть меньше 2 символов!")
    private String first_name;

    @Size(min=2, message = "Имя продавца не может быть меньше 2 символов!")
    private String last_name;

    @NotEmpty(message = "Должность не может быть пустой!")
    private String position;

    @NotEmpty(message = "Дата приема на работу не может быть пустой!")
    private String employment_date;

    @NotEmpty(message = "Дата рождения не может быть пустой!")
    private String date_birth;

    @NotEmpty(message = "Адрес элеектронной почты не может быть пустым!")
    private String email;

    public String getInitial(){
        return this.first_name+" "+this.last_name;
    }
}
