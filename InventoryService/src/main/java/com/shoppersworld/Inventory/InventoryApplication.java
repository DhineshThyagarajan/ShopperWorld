package com.shoppersworld.Inventory;

import com.shoppersworld.Inventory.model.Inventory;
import com.shoppersworld.Inventory.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	public CommandLineRunner loaddata (InventoryRepository irs){
		return args->{
			Inventory i1= new Inventory();
			i1.setProduct("One plus");
			i1.setQuantity(200);

			Inventory i2= new Inventory();
			i2.setProduct("Iphone");
			i2.setQuantity(0);
			irs.save(i1);
			irs.save(i2);


		};

	}

}
