package pl.peterdev.invoices.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.jackson.datatype.money.MoneyModule;

@Configuration
public class SpringConfig {
  @Bean
  public MoneyModule moneyModule() {
    return new MoneyModule().withQuotedDecimalNumbers();
  }
}
