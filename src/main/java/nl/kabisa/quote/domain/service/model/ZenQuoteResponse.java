package nl.kabisa.quote.domain.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZenQuoteResponse {

    @JsonProperty("q")
    public String quote;

    @JsonProperty("a")
    public String author;

    @JsonProperty("h")
    public String quoteHtml;
}