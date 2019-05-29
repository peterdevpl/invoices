package pl.peterdev.invoices.domain.exception;

public final class InvoiceIsEmpty extends RuntimeException {
  public InvoiceIsEmpty() {
    super("Invoice is empty");
  }
}
