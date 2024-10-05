package com.library.e_library.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String mobileNumber;
    @Column(unique = true)
    private String email;
    private SubscriptionStatus subscriptionStatus;

    private enum SubscriptionStatus{
        ACTIVE,INACTIVE
    }
}
