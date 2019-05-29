package pl.peterdev.invoices.domain;

import pl.peterdev.invoices.domain.exception.InvoiceAlreadyExists;
import pl.peterdev.invoices.domain.exception.InvoiceNotFound;

import java.util.Collection;

public interface InvoicesRepository {
  Collection<Invoice> findAll();

  Invoice get(InvoiceId invoiceId) throws InvoiceNotFound;

  void add(Invoice invoice) throws InvoiceAlreadyExists;

  void update(InvoiceId id, Invoice invoice) throws InvoiceNotFound;

  void remove(InvoiceId id) throws InvoiceNotFound;
}
