package pl.peterdev.invoices.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.peterdev.invoices.domain.tax.VatId;

public final class Sender extends Contractor {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Sender(@JsonProperty("name") String name,
                  @JsonProperty("address") Address address,
                  @JsonProperty("vatId") VatId vatId) {
        super(name, address, vatId);
    }
}
