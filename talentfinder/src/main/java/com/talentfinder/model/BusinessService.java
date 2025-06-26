package com.talentfinder.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_seq")
    @SequenceGenerator(name = "service_seq", sequenceName = "service_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String title;

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

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Video> videos;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public void addVideos(List<Video> videos) {
        for (Video v : videos) {
            v.setService(this);
        }
        this.videos = videos;
    }

    public void addPictures(List<Picture> pictures) {
        for (Picture p : pictures) {
            p.setService(this);
        }
        this.pictures = pictures;
    }
}
