package pl.peterdev.invoices.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.math.BigDecimal;

final class InvoiceItemTest {
  @Test
  void shouldReturnTotalMonetaryAmounts() {
    // given
    CurrencyUnit currency = Monetary.getCurrency("EUR");
    Money amount = Money.of(10000, currency);
    InvoiceItem item = new InvoiceItem("Test item", BigDecimal.valueOf(2), amount, VatRate.valueOf(23));

    // then
    Money expectedTotalNet = Money.of(20000, currency);
    Money expectedTotalVat = Money.of(4600, currency);
    Money expectedTotalGross = Money.of(24600, currency);

    assertTrue(item.totalNetAmount().isEqualTo(expectedTotalNet));
    assertTrue(item.totalVatAmount().isEqualTo(expectedTotalVat));
    assertTrue(item.totalGrossAmount().isEqualTo(expectedTotalGross));
  }
}
