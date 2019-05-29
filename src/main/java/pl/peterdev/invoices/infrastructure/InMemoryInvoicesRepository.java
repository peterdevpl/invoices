package pl.peterdev.invoices.infrastructure;

import org.springframework.stereotype.Component;
import pl.peterdev.invoices.domain.Invoice;
import pl.peterdev.invoices.domain.InvoiceId;
import pl.peterdev.invoices.domain.InvoicesRepository;
import pl.peterdev.invoices.domain.exception.InvoiceAlreadyExists;
import pl.peterdev.invoices.domain.exception.InvoiceNotFound;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
final class InMemoryInvoicesRepository implements InvoicesRepository {
  private final Map<InvoiceId, Invoice> invoices = new HashMap<>();

  @Override
  public Collection<Invoice> findAll() {
    return invoices.values();
  }

  @Override
  public Invoice get(InvoiceId id) throws InvoiceNotFound {
    if (!invoices.containsKey(id)) {
      throw new InvoiceNotFound(id);
    }

    return invoices.get(id);
  }

  @Override
  public void add(Invoice invoice) throws InvoiceAlreadyExists {
    if (invoices.containsKey(invoice.getNumber())) {
      throw new InvoiceAlreadyExists(invoice.getNumber());
    }

    invoices.put(invoice.getNumber(), invoice);
  }

  @Override
  public void update(InvoiceId id, Invoice invoice) {
    if (!invoices.containsKey(id)) {
      throw new InvoiceNotFound(id);
    }

    invoices.put(id, invoice);
  }

  @Override
  public void remove(InvoiceId id) throws InvoiceNotFound {
    if (!invoices.containsKey(id)) {
      throw new InvoiceNotFound(id);
    }

    invoices.remove(id);
  }
}
