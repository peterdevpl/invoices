package pl.peterdev.invoices.domain.tax;

import lombok.Getter;

import java.math.BigDecimal;

public final class VatRate {
  @Getter
  private final BigDecimal rate;

  public VatRate(BigDecimal rate) {
    if ((-1 == rate.compareTo(BigDecimal.ZERO)) || (1 == rate.compareTo(BigDecimal.valueOf(100)))) {
      throw new IllegalArgumentException("VAT rate must be between 0.0 and 1.0");
    }

    this.rate = rate;
  }

  public static VatRate valueOf(long rate) {
    return new VatRate(BigDecimal.valueOf(rate).movePointLeft(2));
  }
}
