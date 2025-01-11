package net.buuuu.orderservice.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")  // Use a custom name for the table to avoid the reserved keyword "order"
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class Order {
    @Id
    private String id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;
}
