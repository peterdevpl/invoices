package pl.peterdev.invoices.domain;

import pl.peterdev.invoices.domain.tax.VatId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class Recipient extends Contractor {
    public Recipient(@NotBlank String name, @NotNull Address address, @NotNull VatId vatId) {
        super(name, address, vatId);
    }
}
