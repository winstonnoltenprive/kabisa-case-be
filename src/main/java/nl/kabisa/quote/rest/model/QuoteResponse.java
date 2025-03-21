package nl.kabisa.quote.rest.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JacksonXmlRootElement
public class QuoteResponse {

    private Long id;
    private String text;
    private String author;

}
