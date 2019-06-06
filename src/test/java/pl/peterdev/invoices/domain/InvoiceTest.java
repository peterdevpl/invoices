package pl.peterdev.invoices.domain;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import pl.peterdev.invoices.domain.exception.InvoiceIsEmpty;
import pl.peterdev.invoices.domain.exception.InvoiceItemsDoNotMatchInvoiceCurrency;
import pl.peterdev.invoices.domain.payment.BankAccountFixture;
import pl.peterdev.invoices.domain.payment.PaymentTerms;
import pl.peterdev.invoices.domain.tax.VatRate;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class InvoiceTest {
  @Test
  void shouldReturnTotalAmount() {
    // given
    CurrencyUnit currency = Monetary.getCurrency("EUR");
    Money amount = Money.of(10000, currency);
    Invoice invoice = new Invoice(new InvoiceId("1"),
        currency,
        ContractorFixture.exampleSender(),
        ContractorFixture.exampleRecipient(),
        new PaymentTerms(BankAccountFixture.exampleAccount(), 15),
        LocalDate.of(2019, 1, 1),
        Collections.singletonList(new InvoiceItem("Test product", BigDecimal.ONE, amount, VatRate.valueOf(23))));

    // then
    assertTrue(invoice.getTotalNetAmount().isEqualTo(Money.of(10000, currency)));
    assertTrue(invoice.getTotalVatAmount().isEqualTo(Money.of(2300, currency)));
    assertTrue(invoice.getTotalGrossAmount().isEqualTo(Money.of(12300, currency)));
  }

  @Test
  void shouldCalculateDueDate() {
    // given
    CurrencyUnit currency = Monetary.getCurrency("EUR");
    Money amount = Money.of(10000, currency);
    Invoice invoice = new Invoice(new InvoiceId("1"),
        currency,
        ContractorFixture.exampleSender(),
        ContractorFixture.exampleRecipient(),
        new PaymentTerms(BankAccountFixture.exampleAccount(), 15),
        LocalDate.of(2019, 1, 1),
        Collections.singletonList(new InvoiceItem("Test product", BigDecimal.ONE, amount, VatRate.valueOf(23))));

    // then
    LocalDate expectedDueDate = LocalDate.of(2019, 1, 16);
    Optional<LocalDate> actualDueDate = invoice.getDueDate();
    assertTrue(actualDueDate.isPresent());
    assertEquals(expectedDueDate, actualDueDate.get());
  }

  @Test
  void shouldThrowExceptionForAnEmptyInvoice() {
    // given
    CurrencyUnit currency = Monetary.getCurrency("EUR");
    Money amount = Money.of(10000, currency);

    // expect
    assertThrows(InvoiceIsEmpty.class, () -> new Invoice(new InvoiceId("1"),
        currency,
        ContractorFixture.exampleSender(),
        ContractorFixture.exampleRecipient(),
        new PaymentTerms(BankAccountFixture.exampleAccount(), 15),
        LocalDate.of(2019, 1, 1),
        new ArrayList<>()));
  }

  @Test
  void shouldThrowExceptionForAWrongItemCurrency() {
    // given
    Money amount = Money.of(10000, Monetary.getCurrency("GBP"));

    // expect
    assertThrows(InvoiceItemsDoNotMatchInvoiceCurrency.class, () -> new Invoice(new InvoiceId("1"),
        Monetary.getCurrency("EUR"),
        ContractorFixture.exampleSender(),
        ContractorFixture.exampleRecipient(),
        new PaymentTerms(BankAccountFixture.exampleAccount(), 15),
        LocalDate.of(2019, 1, 1),
        Collections.singletonList(new InvoiceItem("Test product", BigDecimal.ONE, amount, VatRate.valueOf(23)))));
    }
}
