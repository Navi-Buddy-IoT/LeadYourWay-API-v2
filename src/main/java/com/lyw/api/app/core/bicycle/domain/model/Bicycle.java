package com.lyw.api.app.core.bicycle.domain.model;

import com.lyw.api.app.assets.domain.model.Gps;
import com.lyw.api.app.assets.domain.model.Temperature;
import com.lyw.api.app.assets.domain.model.Velocity;
import com.lyw.api.app.iam.identity.domain.model.User;
import com.lyw.api.app.shared.model.AuditModel;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bicycles")
public class Bicycle extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bicycle_name", nullable = false)
    private String bicycleName;

    @Column(name = "bicycle_description", nullable = false)
    private String bicycleDescription;

    @Column(name = "bicycle_price", nullable = false)
    private double bicyclePrice;

    @Column(name = "bicycle_size", nullable = false)
    private String bicycleSize;

    @Column(name = "bicycle_model", nullable = true)
    private String bicycleModel;

    @Column(name = "image_data", nullable = true)
    private String imageData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "temperature_id")
    private Temperature temperature;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "velocity_id")
    private Velocity velocity;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gps_id")
    private Gps gps;
}
