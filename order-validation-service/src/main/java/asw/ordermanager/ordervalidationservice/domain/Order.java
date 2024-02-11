package asw.ordermanager.ordervalidationservice.domain;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/* Ordine. */
@Entity
@Table(name="ORDERS")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order implements Comparable<Order> {

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	private String customer;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<OrderItem> orderItems;
	//private double total;

	public Order(String customer, List<OrderItem> orderItems) {
		this();
		this.customer = customer;
		this.orderItems = orderItems;
		//this.total = total;
	}

	@Override
	public int compareTo(Order other) {
		return this.id.compareTo(other.id); 
	}
	
}
