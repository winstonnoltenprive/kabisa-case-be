package nl.kabisa.quote.domain.repository.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_likes") // table name only
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private QuoteUser user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quote_id")
    private Quote quote;
}