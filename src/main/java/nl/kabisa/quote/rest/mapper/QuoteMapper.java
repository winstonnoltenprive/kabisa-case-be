package nl.kabisa.quote.rest.mapper;

import nl.kabisa.quote.domain.repository.model.Quote;
import nl.kabisa.quote.rest.model.QuoteResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuoteMapper {

    QuoteResponse map(Quote quote);
}
