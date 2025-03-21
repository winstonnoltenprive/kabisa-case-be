package nl.kabisa.quote.rest.mapper;

import javax.annotation.processing.Generated;
import nl.kabisa.quote.domain.repository.model.Quote;
import nl.kabisa.quote.rest.model.QuoteResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T14:37:47+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class QuoteMapperImpl implements QuoteMapper {

    @Override
    public QuoteResponse map(Quote quote) {
        if ( quote == null ) {
            return null;
        }

        QuoteResponse quoteResponse = new QuoteResponse();

        quoteResponse.setId( quote.getId() );
        quoteResponse.setText( quote.getText() );
        quoteResponse.setAuthor( quote.getAuthor() );

        return quoteResponse;
    }
}
