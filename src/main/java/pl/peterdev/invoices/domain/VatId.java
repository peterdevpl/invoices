package pl.peterdev.invoices.domain;

public final class VatId {
  private final String id;

  public VatId(String id) {
    this.id = id;
  }

  public String id() {
    return id;
  }
}
