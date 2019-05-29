package pl.peterdev.invoices.domain.exception;

import pl.peterdev.invoices.domain.InvoiceId;

public final class InvoiceNotFound extends RuntimeException {
  public InvoiceNotFound(InvoiceId id) {
    super("Cannot find invoice with ID " + id);
  }
}
