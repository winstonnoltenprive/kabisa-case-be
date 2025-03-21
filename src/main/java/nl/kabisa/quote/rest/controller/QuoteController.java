package nl.kabisa.quote.rest.controller;

import lombok.RequiredArgsConstructor;
import nl.kabisa.quote.domain.repository.model.Quote;
import nl.kabisa.quote.domain.service.QuoteService;
import nl.kabisa.quote.rest.mapper.QuoteMapper;
import nl.kabisa.quote.rest.mapper.QuoteUserMapper;
import nl.kabisa.quote.rest.model.QuoteResponse;
import nl.kabisa.quote.rest.model.QuoteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class QuoteController {

    @Autowired
    private QuoteService quoteService;
    @Autowired
    private QuoteUserMapper quoteUserMapper;
    @Autowired
    private QuoteMapper quoteMapper;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/quote",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<QuoteResponse> getQuote(@RequestBody(required = false) QuoteUser quoteUser) {

        nl.kabisa.quote.domain.repository.model.QuoteUser user = quoteUserMapper.map(quoteUser);

        Quote quote = quoteService.getQuote(user);

        QuoteResponse quoteResponse = quoteMapper.map(quote);

        return ResponseEntity.ok(quoteResponse);

    }


}
