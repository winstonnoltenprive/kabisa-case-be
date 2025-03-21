package nl.kabisa.quote.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
