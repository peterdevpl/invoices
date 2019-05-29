package pl.peterdev.invoices.domain.tax;

import lombok.Value;

import java.math.BigDecimal;

@Value
public final class VatRate {
  private final BigDecimal rate;
  private final String name;

  public VatRate(BigDecimal rate) {
    validate(rate);
    this.rate = rate;
    this.name = rate.movePointRight(2).stripTrailingZeros().toString() + '%';
  }

  public VatRate(String rateString) {
    BigDecimal rate = new BigDecimal(rateString);
    validate(rate);
    this.rate = rate;
    this.name = rate.movePointRight(2).stripTrailingZeros().toString() + '%';
  }

  private void validate(BigDecimal rate) {
    if ((rate.compareTo(BigDecimal.ZERO) < 0) || (rate.compareTo(BigDecimal.valueOf(100)) > 0)) {
      throw new IllegalArgumentException("VAT rate must be between 0.0 and 1.0");
    }
  }

  public String toString() {
    return name;
  }

  public static VatRate valueOf(long rate) {
    return new VatRate(BigDecimal.valueOf(rate).movePointLeft(2));
  }
}
