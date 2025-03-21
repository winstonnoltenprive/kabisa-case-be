package nl.kabisa.quote.domain.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nl.kabisa.quote.domain.repository.LikeRepository;
import nl.kabisa.quote.domain.repository.QuoteRepository;
import nl.kabisa.quote.domain.repository.model.Quote;
import nl.kabisa.quote.domain.repository.model.QuoteUser;
import nl.kabisa.quote.domain.service.model.DummyJsonResponse;
import nl.kabisa.quote.domain.service.model.ZenQuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private static final String ZEN_QUOTES_API_URL = "https://zenquotes.io/api/random";
    private static final String DUMMY_QUOTES_API_URL = "https://dummyjson.com/quotes";

    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private LikeRepository likeRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Long> allQuoteIds;

    @PostConstruct
    public void initializeQuotes() {
        if (quoteRepository.count() == 0) {
            fetchAndStoreQuotes();
        }
        refreshQuoteIds();
    }

    @Scheduled(fixedRate = 86400000)
    @Transactional
    public void fetchAndStoreQuotes() {
        List<Quote> newQuotes = fetchQuotesFromApi();
        quoteRepository.saveAll(newQuotes);
        refreshQuoteIds();
    }

    private void refreshQuoteIds() {
        allQuoteIds = quoteRepository.findAll().stream()
                .map(Quote::getId)
                .toList();
    }

    public Quote getRandomQuote() {
        if (allQuoteIds.isEmpty()) return null;
        int randomIndex = ThreadLocalRandom.current().nextInt(allQuoteIds.size());
        Long randomId = allQuoteIds.get(randomIndex);
        return quoteRepository.findById(randomId).orElse(null);
    }

    public Quote getLikedQuote() {
        List<Quote> topLikedQuotes = likeRepository.findTopLikedQuotes();
        if (topLikedQuotes.isEmpty()) return getRandomQuote();
        int limit = Math.max(1, topLikedQuotes.size() / 10);
        int randomIndex = ThreadLocalRandom.current().nextInt(limit);
        return topLikedQuotes.get(randomIndex);
    }

    private List<Quote> fetchQuotesFromApi() {
        try {
            DummyJsonResponse response =
                    restTemplate.getForObject(DUMMY_QUOTES_API_URL, DummyJsonResponse.class);
            if (response != null && response.quotes != null) {
                return response.quotes.stream()
                        .map(q -> new Quote(null, q.quote, q.author))
                        .toList();
            }
        } catch (Exception ignored) {
        }

        try {
            String json = restTemplate.getForObject(ZEN_QUOTES_API_URL, String.class);
            List<ZenQuoteResponse> zenQuotes = objectMapper.readValue(json, new TypeReference<>() {
            });
            return zenQuotes.stream()
                    .map(z -> new Quote(null, z.quote, z.author))
                    .toList();
        } catch (Exception ignored) {
        }

        return Collections.emptyList();
    }

    public Quote getQuote(QuoteUser quoteUser) {
        if (quoteUser == null) {
            return getLikedQuote();
        } else {
            return getRandomQuote();
        }
    }
}