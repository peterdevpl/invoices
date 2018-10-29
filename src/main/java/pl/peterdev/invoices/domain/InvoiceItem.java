package pl.peterdev.invoices.domain;

import javax.money.MonetaryAmount;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public final class InvoiceItem {
  private String name;
  private BigDecimal quantity;
  private MonetaryAmount unitNetAmount;
  private VatRate vatRate;

  public InvoiceItem(@NotBlank String name, @NotNull BigDecimal quantity, @NotNull MonetaryAmount unitNetAmount, @NotNull VatRate vatRate) {
    this.name = name;
    this.quantity = quantity;
    this.unitNetAmount = unitNetAmount;
    this.vatRate = vatRate;
  }

  public String name() {
    return name;
  }

  public BigDecimal quantity() {
    return quantity;
  }

  public MonetaryAmount unitNetPrice() {
    return unitNetAmount;
  }

  public VatRate vatRate() {
    return vatRate;
  }

  public MonetaryAmount totalNetAmount() {
    return unitNetAmount.multiply(quantity);
  }

  public MonetaryAmount totalVatAmount() {
    return totalNetAmount().multiply(vatRate.rate());
  }

  public MonetaryAmount totalGrossAmount() {
    return totalNetAmount().add(totalVatAmount());
  }
}
