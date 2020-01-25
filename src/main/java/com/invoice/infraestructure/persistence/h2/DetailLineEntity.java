package com.invoice.infraestructure.persistence.h2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "DETAIL_LINE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
public class DetailLineEntity {
  @Id
  @Type(type = "uuid-char")
  private UUID id;
  private String product;
  private int amount;
  private double price;
  @ManyToOne(fetch = FetchType.LAZY)
  private InvoiceEntity invoice;
}
