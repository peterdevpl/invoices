package pl.peterdev.invoices.domain;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import pl.peterdev.invoices.domain.tax.VatRate;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

final class InvoiceTest {
  @Test
  void shouldReturnTotalAmount() {
    // given
    CurrencyUnit currency = Monetary.getCurrency("EUR");
    Invoice invoice = new Invoice(currency, ContractorFixture.exampleSender(), ContractorFixture.exampleRecipient());
    Money amount = Money.of(10000, currency);
    invoice.addItem("Test product", BigDecimal.ONE, amount, VatRate.valueOf(23));

    // when
    MonetaryAmount total = invoice.totalGrossAmount();

    // then
    assertTrue(total.isEqualTo(Money.of(12300, currency)));
  }
}
