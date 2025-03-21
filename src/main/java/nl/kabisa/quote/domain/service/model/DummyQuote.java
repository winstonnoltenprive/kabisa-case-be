package nl.kabisa.quote.domain.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyQuote {
    public String quote;
    public String author;
}