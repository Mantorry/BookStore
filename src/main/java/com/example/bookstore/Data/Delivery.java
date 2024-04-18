package com.example.bookstore.Data;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Дата заказа не может быть пустой!")
    private String date_input;

    @NotEmpty(message = "Дата отгрузки заказа не может быть пустой!")
    private String date_output;

    @Min(message = "Кол-во книг не может быть меньше 1!", value = 1)
    private int quantity;

    @Min(message = "Сумма заказа не может быть меньше 1!", value = 1)
    private float total_sum;

    @ManyToOne
    @NotNull(message = "Необходимо выбрать книгу!")
    private Book book;

    @ManyToOne
    @NotNull(message = "Необходимо выбрать продавца!")
    private Sale sale;

    @ManyToOne
    @NotNull(message = "Необходимо выбрать покупателя!")
    private Customer customer;
}
