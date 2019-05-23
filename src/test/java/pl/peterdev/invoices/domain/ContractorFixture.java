package pl.peterdev.invoices.domain;

import pl.peterdev.invoices.domain.tax.VatId;

public final class ContractorFixture {
    public static Sender exampleSender() {
        return new Sender("Test Sender", exampleSenderAddress(), exampleSenderVatId());
    }

    public static Recipient exampleRecipient() {
        return new Recipient("Test Recipient", exampleRecipientAddress(), exampleRecipientVatId());
    }

    private static Address exampleSenderAddress() {
        return new Address("One Street 1", "Warsaw", "01-234");
    }

    private static VatId exampleSenderVatId() {
        return new VatId("PL1234567890");
    }

    private static Address exampleRecipientAddress() {
        return new Address("Second Street 2", "London", "ABC-123");
    }

    private static VatId exampleRecipientVatId() {
        return new VatId("GB987654321");
    }
}
