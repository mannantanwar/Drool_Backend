package com.drool.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;
import org.hibernate.mapping.Join;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SavedPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //konse user ne save lia hai ie saved place me ek user ek se zyada baar aaskta hai
    @ManyToOne
    @JoinColumn(name= "user_id",nullable=false)
    private User user;

    //similarly ek place bhu to multiple baar aaskti hai saved places ke andar
    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    private LocalDateTime savedDate;

    @PrePersist
    public void beforeSave() {
        savedDate = LocalDateTime.now();
    }

}
