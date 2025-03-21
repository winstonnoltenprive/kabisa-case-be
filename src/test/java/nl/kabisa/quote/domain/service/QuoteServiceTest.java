package nl.kabisa.quote.domain.service;

import nl.kabisa.quote.domain.repository.LikeRepository;
import nl.kabisa.quote.domain.repository.QuoteRepository;
import nl.kabisa.quote.domain.repository.model.Quote;
import nl.kabisa.quote.domain.repository.model.QuoteUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuoteServiceTest {

    @Mock
    private QuoteRepository quoteRepository;

    @Mock
    private LikeRepository likeRepository;

    @InjectMocks
    private QuoteService quoteService;

    @BeforeEach
    void setUp() {
        when(quoteRepository.count()).thenReturn(1L);

        Quote storedQuote = new Quote(2L, "Random Quote", "Random Author");
        when(quoteRepository.findAll()).thenReturn(List.of(storedQuote));

        quoteService.initializeQuotes();
    }

    @Test
    void testGetQuote_NullUser_ReturnsLikedQuote() {
        // Arrange: when no user is provided, getQuote should return a liked quote if available.
        Quote likedQuote = new Quote(1L, "Liked Quote", "Liked Author");
        // Simulate likeRepository returning one liked quote.
        when(likeRepository.findTopLikedQuotes()).thenReturn(List.of(likedQuote));

        // Act: Call getQuote with a null QuoteUser.
        Quote result = quoteService.getQuote(null);

        // Assert:
        assertNotNull(result);
        assertEquals("Liked Quote", result.getText());
        assertEquals("Liked Author", result.getAuthor());
    }

    @Test
    void testGetQuote_NonNullUser_ReturnsRandomQuote() {
        // Arrange: When a non-null user is provided, the service returns a random quote.
        // (The initializeQuotes() already set up the random quote with id=2.)
        QuoteUser dummyUser = new QuoteUser();
        dummyUser.setEmail("dummy@example.com");
        dummyUser.setFirstName("Dummy");
        dummyUser.setLastName("User");
        Quote storedQuote = new Quote(2L, "Random Quote", "Random Author");
        when(quoteRepository.findById(2L)).thenReturn(Optional.of(storedQuote));
        // Act: Call getQuote with a non-null QuoteUser.
        Quote result = quoteService.getQuote(dummyUser);

        // Assert:
        assertNotNull(result);
        assertEquals("Random Quote", result.getText());
        assertEquals("Random Author", result.getAuthor());
    }
}