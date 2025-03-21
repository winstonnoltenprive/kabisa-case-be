package nl.kabisa.quote.domain.repository;

import nl.kabisa.quote.domain.repository.model.Like;
import nl.kabisa.quote.domain.repository.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("""
                SELECT l.quote FROM Like l 
                GROUP BY l.quote 
                ORDER BY COUNT(l) DESC
            """)
    List<Quote> findTopLikedQuotes();
}
