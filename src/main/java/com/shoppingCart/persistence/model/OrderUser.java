package com.shoppingCart.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class OrderUser {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToMany(mappedBy = "customer")
    private List<Order> order;
}
