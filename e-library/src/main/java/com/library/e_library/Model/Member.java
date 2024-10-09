package com.library.e_library.Model;

import com.library.e_library.enums.SubscriptionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@With
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    @Email
    @Column(unique = true)
    private String email;
    @Builder.Default
    private SubscriptionStatus subscriptionStatus=SubscriptionStatus.INACTIVE;
}
