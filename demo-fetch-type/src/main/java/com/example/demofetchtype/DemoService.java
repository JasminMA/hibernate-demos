package com.example.demofetchtype;

import com.example.demofetchtype.entity.Customer;
import com.example.demofetchtype.entity.Invoice;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class DemoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void findTotalAmountForAllInvoice() throws HibernateException {

        Session session = null;
        if (entityManager == null
                || (session = entityManager.unwrap(Session.class)) == null) {

            throw new NullPointerException();
        }
        final Query from_customer_ = session.createQuery("FROM Customer ");
        final List<Customer> customers = from_customer_.list();
        System.out.println("Load all Customers:");
//        Criteria crit = session.createCriteria(Customer.class);
//        @SuppressWarnings("unchecked")
//        List<Customer> customers = crit.list();
        System.out.println("Number of Customers: " + customers.size());
//        BigDecimal allCustomerTotal = BigDecimal.ZERO;
        for (Customer customer : customers) {
            System.out.println(String.format("Fetch the collection of Invoices for " +
                    "Customer %s %s", customer.getFirstName(), customer.getLastName()));
            Set<Invoice> invoices = customer.getInvoices();
            for (Invoice invoice : invoices) {
                invoice.getName();
//                System.out.println("Invoice Name => " + invoice.getName());
            }
        }
//        System.out.println("Total amount for all invoices: " + allCustomerTotal.toString());
        session.close();
    }

}
