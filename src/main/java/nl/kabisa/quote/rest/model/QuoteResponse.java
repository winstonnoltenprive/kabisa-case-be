package nl.kabisa.quote.rest.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuoteResponse {

    private Long id;
    private String text;
    private String author;

}
