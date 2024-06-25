package com.lyw.api.app.assets.domain.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.lyw.api.app.shared.model.AuditModel;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "gpses")
public class Gps extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    
    @Column(name = "longitude", nullable = false)
    private double longitude;
    
}
