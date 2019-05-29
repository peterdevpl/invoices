package pl.peterdev.invoices.presentation.http;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.peterdev.invoices.domain.Invoice;
import pl.peterdev.invoices.domain.InvoiceId;
import pl.peterdev.invoices.domain.InvoicesRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
final class InvoicesController {
  private final InvoicesRepository invoices;

  @GetMapping("/invoices")
  List<Invoice> findInvoices() {
    return new ArrayList<>(invoices.findAll());
  }

  @GetMapping("/invoices/{id}")
  Invoice getInvoice(@PathVariable InvoiceId id) {
    return invoices.get(id);
  }

  @PostMapping("/invoices")
  void addInvoice(@RequestBody Invoice invoice) {
    invoices.add(invoice);
  }

  @PutMapping("/invoices/{id}")
  void updateInvoice(@RequestBody Invoice invoice, @PathVariable InvoiceId id) {
    invoices.update(id, invoice);
  }

  @DeleteMapping("/invoices/{id}")
  void removeInvoice(@PathVariable InvoiceId id) {
    invoices.remove(id);
  }
}
