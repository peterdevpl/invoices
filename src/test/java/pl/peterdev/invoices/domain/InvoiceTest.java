package pl.peterdev.invoices.domain;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import pl.peterdev.invoices.domain.payment.BankAccountFixture;
import pl.peterdev.invoices.domain.payment.PaymentTerms;
import pl.peterdev.invoices.domain.tax.VatRate;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

  @Test
  void shouldCalculateDueDate() {
    // given
    CurrencyUnit currency = Monetary.getCurrency("EUR");
    Invoice invoice = new Invoice(currency, ContractorFixture.exampleSender(), ContractorFixture.exampleRecipient());
    PaymentTerms paymentTerms = new PaymentTerms(BankAccountFixture.exampleAccount(), 15);
    invoice.setIssueDate(LocalDate.of(2019, 1, 1));
    invoice.setPaymentTerms(paymentTerms);

    // then
    LocalDate expectedDueDate = LocalDate.of(2019, 1, 16);
    Optional<LocalDate> actualDueDate = invoice.getDueDate();
    assertTrue(actualDueDate.isPresent());
    assertEquals(expectedDueDate, actualDueDate.get());
  }

  @Test
  void shouldNotCalculateDueDateForEmptyPaymentTerms() {
    // given
    CurrencyUnit currency = Monetary.getCurrency("EUR");
    Invoice invoice = new Invoice(currency, ContractorFixture.exampleSender(), ContractorFixture.exampleRecipient());
    invoice.setIssueDate(LocalDate.of(2019, 1, 1));

    // then
    Optional<LocalDate> actualDueDate = invoice.getDueDate();
    assertFalse(actualDueDate.isPresent());
  }
}
