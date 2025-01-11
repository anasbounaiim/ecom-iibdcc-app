package net.buuuu.orderservice.repositories;

import net.buuuu.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order , String> {
}
