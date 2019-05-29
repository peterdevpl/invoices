package pl.peterdev.invoices.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.peterdev.invoices.domain.tax.VatId;

@Getter
@RequiredArgsConstructor
abstract class Contractor {
  private final String name;
  private final Address address;
  private final VatId vatId;
}
