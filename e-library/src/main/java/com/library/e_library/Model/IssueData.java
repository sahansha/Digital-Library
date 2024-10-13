package com.library.e_library.Model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.library.e_library.enums.IssueStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@With
public class IssueData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    //@JsonIgnore
    @JsonIncludeProperties({"id","firstName","lastName"})
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    //@JsonIgnore
    @JsonIncludeProperties({"id","name","author"})
    private Book book;

    @Builder.Default
    private Instant createdAt=Instant.now();
    private Instant expirationDate;

    @NotNull
    private double subscriptionAmount;

    @NotNull
    @Builder.Default
    private IssueStatus issueStatus=IssueStatus.ISSUED;

  /*  @JsonProperty(value = "bookId")
    public UUID getBookId()
    {
        return this.book.getId();
    }*/
   /* @JsonProperty(value = "memberId")
    public UUID getMemberId()
    {
        return this.member.getId();
    }*/


    public Instant calculateExpirationdate()
    {
        this.expirationDate=this.createdAt.plus(15, ChronoUnit.DAYS);
        return this.expirationDate;
    }
    public double calculateSubscriptionAmount()
    {
        this.subscriptionAmount= this.book.getPrice()*0.05D;
        return this.subscriptionAmount;
    }


    @Getter
    @AllArgsConstructor
    public static class ExceptionDetails {
        private LocalDateTime dateTime;
        private String message;
        private String description;
    }
}
