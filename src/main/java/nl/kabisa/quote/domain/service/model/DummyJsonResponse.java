package nl.kabisa.quote.domain.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyJsonResponse {
    public List<DummyQuote> quotes;
}