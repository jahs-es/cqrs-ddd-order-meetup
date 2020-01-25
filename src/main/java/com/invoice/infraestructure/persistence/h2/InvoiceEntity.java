package com.invoice.infraestructure.persistence.h2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@DynamicUpdate
@Table(name = "INVOICE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID id;
    private double total;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice", cascade = CascadeType.ALL)
    private Set<DetailLineEntity> detailLines = new HashSet<>();
}
