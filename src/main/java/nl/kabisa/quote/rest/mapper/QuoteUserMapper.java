package nl.kabisa.quote.rest.mapper;

import nl.kabisa.quote.domain.repository.model.QuoteUser;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuoteUserMapper {

    QuoteUser map(nl.kabisa.quote.rest.model.QuoteUser quoteUser);
}
