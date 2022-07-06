package com.example.demofetchtype.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String street;

    @Column
    private String city;


    /*
        n+1 queries => 1001
        lazy loading
        1 select customers
        for each customer select the invoices => 1000
     */
//    @OneToMany(mappedBy = "customer")
//    @Fetch(FetchMode.SELECT)
//    private Set<Invoice> invoices;


    /*
        no queries => 21
        Hibernate will try to load the invoices' collection in batches defined by the size parameter.
     */
//    @OneToMany(mappedBy = "customer")
//    @Fetch(FetchMode.SELECT)
//    @BatchSize(size = 50)
//    private Set<Invoice> invoices;


    /*
    no.Queries => 1001
    eagerly loading
     */
//    @OneToMany(mappedBy = "customer")
//    @Fetch(FetchMode.JOIN)
//    private Set<Invoice> invoices;


    /*
      no.Queries => 3
    A SUBSELECT generates one query to load the Customers and one additional query to fetch all the Invoice collections.
     It is important to notice that all Invoices are loaded for which there is a corresponding Customer in the database.
     So even Invoice collections for who there are no matching Customers in the session will be retrieved.

     select id, city, first_name, last_name, street from customer;

    select customer_id, id, id, customer_id, name
    from invoice where customer_id in (select customer0_.id from customer customer0_)
     */
    @OneToMany(mappedBy = "customer")
    @Fetch(FetchMode.SUBSELECT)
    private Set<Invoice> invoices;

}
