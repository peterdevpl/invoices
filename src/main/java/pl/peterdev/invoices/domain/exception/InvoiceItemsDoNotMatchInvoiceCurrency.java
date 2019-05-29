package pl.peterdev.invoices.domain.exception;

import javax.money.CurrencyUnit;

public final class InvoiceItemsDoNotMatchInvoiceCurrency extends RuntimeException {
  public InvoiceItemsDoNotMatchInvoiceCurrency(CurrencyUnit invoiceCurrency) {
    super("Invoice items do not match invoice currency, which is " + invoiceCurrency.getCurrencyCode());
  }
}
