package com.baluwo.challenge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BUY_TRANSACTION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyTransactionEntity {
    @Id
    @Column(name = "ID_BUY_TRANSACTION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUSTOMER")
    private CustomerEntity customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyTransaction")
    private List<ProductEntity> products = new ArrayList<>();

    public void addProduct(ProductEntity product) {
        products.add(product);
        product.setBuyTransaction(this);
    }

    public void removeProduct(ProductEntity product) {
        products.remove(product);
        product.setBuyTransaction(null);
    }
}
