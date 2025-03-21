package nl.kabisa.quote.domain.repository;

import nl.kabisa.quote.domain.repository.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
