package asw.ordermanager.ordervalidationservice.domain;

import jakarta.persistence.*;
import lombok.*;

/* Prodotto con inventario. */
@Entity
@Table(name="PRODUCTS")
@Data 
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Comparable<Product> {

	@Id
	@EqualsAndHashCode.Include
	private String name; 
	/* quantità disponibile */ 
	private int stockLevel; 
	/* prezzo di listino */ 
	//private double price;

	public Product(String name, int stockLevel){
		this();
		this.name = name;
		this.stockLevel = stockLevel;
		//this.price = price;
	}

	@Override
	public int compareTo(Product other) {
		return this.name.compareTo(other.name); 
	}

}
