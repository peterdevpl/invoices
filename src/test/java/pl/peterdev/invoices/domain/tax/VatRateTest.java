package pl.peterdev.invoices.domain.tax;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class VatRateTest {
  @Test
  void shouldAllowProperValues() {
    // given
    VatRate rateZero = VatRate.valueOf(0);
    VatRate rateHundred = VatRate.valueOf(100);

    // then
    assertEquals(BigDecimal.ZERO, rateZero.getRate());
    assertEquals("0%", rateZero.getName());
    assertEquals("0%", rateZero.toString());
    assertEquals(BigDecimal.ONE, rateHundred.getRate());
    assertEquals("100%", rateHundred.getName());
    assertEquals("100%", rateHundred.toString());
  }

  @Test
  void shouldDisallowUnaryValue() {
    assertThrows(IllegalArgumentException.class, () -> VatRate.valueOf(-1));
  }

  @Test
  void shouldDisallowValueBiggerThanOne() {
    assertThrows(IllegalArgumentException.class, () -> VatRate.valueOf(101));
  }
}
