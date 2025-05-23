package com.talentfinder.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "service")
public class BusinessService {
    @Id
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ServiceCategory category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Video> videos;
}
