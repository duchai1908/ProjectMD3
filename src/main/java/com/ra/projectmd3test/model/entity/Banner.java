package com.ra.projectmd3test.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "banners")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String image;
    private Boolean status;
}
