package pl.peterdev.invoices.domain.exception;

import pl.peterdev.invoices.domain.InvoiceId;

public final class InvoiceAlreadyExists extends RuntimeException {
  public InvoiceAlreadyExists(InvoiceId id) {
    super("Invoice already exists with an ID " + id);
  }
}
