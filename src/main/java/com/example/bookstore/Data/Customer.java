package com.example.bookstore.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, message = "Фамилия продавца не может быть меньше 2 символов!")
    private String first_name;

    @Size(min=2, message = "Имя покупателя не может быть меньше 2 символов!")
    private String last_name;

    @Size(message = "Номер телефона не может быть больше 13 и меньше 3 цифр", max = 13, min = 3)
    private String phone;

    @NotEmpty(message = "Адрес электронной почты не может быть пустым")
    private String email;


    public String getInitial(){
        return this.first_name+" "+this.last_name;
    }
}
