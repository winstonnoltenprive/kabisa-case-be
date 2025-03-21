package nl.kabisa.quote.rest.mapper;

import javax.annotation.processing.Generated;
import nl.kabisa.quote.rest.model.QuoteUser;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T14:37:47+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class QuoteUserMapperImpl implements QuoteUserMapper {

    @Override
    public nl.kabisa.quote.domain.repository.model.QuoteUser map(QuoteUser quoteUser) {
        if ( quoteUser == null ) {
            return null;
        }

        nl.kabisa.quote.domain.repository.model.QuoteUser quoteUser1 = new nl.kabisa.quote.domain.repository.model.QuoteUser();

        quoteUser1.setId( quoteUser.getId() );
        quoteUser1.setFirstName( quoteUser.getFirstName() );
        quoteUser1.setLastName( quoteUser.getLastName() );
        quoteUser1.setEmail( quoteUser.getEmail() );

        return quoteUser1;
    }
}
