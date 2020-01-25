package com.invoice.domain.invoice.detail_line;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Line {
  private String product;
  private int amount;
  private double price;

  public Line(String product, int amount, double price) {
    this.product = product;
    amount(amount);
    price(price);
  }

  private void price(double price) {
    if (price <= 0) {
      throw new DetailLinePriceMustBeAboveZeroException("Price must be above 0 euros");
    }
    this.price = price;
  }

  private void amount(int amount) {
    if (amount < 0) {
      throw new InvalidDetailLineAmount("Amount of a line must be between greater than 0");
    }
    this.amount = amount;
  }

  public double getTotal() {
    return amount * price;
  }
}
