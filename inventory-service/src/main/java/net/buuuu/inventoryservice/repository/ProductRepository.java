package net.buuuu.inventoryservice.repository;

import net.buuuu.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product , String>{
}
