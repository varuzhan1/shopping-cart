package com.shoppingCart.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private OrderUser customer;

}
