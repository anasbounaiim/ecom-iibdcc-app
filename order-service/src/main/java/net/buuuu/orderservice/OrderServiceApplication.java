package net.buuuu.orderservice;

import net.buuuu.orderservice.entities.Order;
import net.buuuu.orderservice.entities.OrderState;
import net.buuuu.orderservice.entities.ProductItem;
import net.buuuu.orderservice.model.Product;
import net.buuuu.orderservice.repositories.OrderRepository;
import net.buuuu.orderservice.repositories.ProductItemRepository;
import net.buuuu.orderservice.restclients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			OrderRepository orderRepository,
			ProductItemRepository productItemRepository,
			InventoryRestClient inventoryRestClient
	) {
		return args -> {
			// Get all products from the inventory
			//List<Product> allProducts = inventoryRestClient.getAllProducts();
			List<String> productsIds = List.of("P01", "P02", "P03");

			// Loop through the products and create orders and product items
			for (int i = 0; i < 5; i++) {
				// Create a new Order instance
				Order order = Order.builder()
						.id(UUID.randomUUID().toString()) // Generate a unique ID
						.date(LocalDate.now()) // Set the current date
						.state(OrderState.PENDING)
						.build();

				Order SavedOrder = orderRepository.save(order);

				productsIds.forEach(productIds -> {
					ProductItem productItem = ProductItem.builder()
							.productId(productIds) // Get product ID from the product
							.quantity(new Random().nextInt(20)) // Generate a random quantity between 0 and 19
							.price(Math.random()*6000) // Get the price of the product
							.order(SavedOrder) // Link the product item to the current order
							.build();

					productItemRepository.save(productItem);

				});

			}
		};

	}


}
