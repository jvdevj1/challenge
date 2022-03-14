package com.baluwo.challenge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CUSTOMER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @Column(name = "ID_CUSTOMER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DOCUMENT_ID")
    @NotNull
    private String documentId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;
}
