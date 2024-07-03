package com.omkar_smartcontactmanager.scm.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Contact {
    @Id
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(length = 1000)
    private String description;
    private boolean favorite = false;

    private String websiteLink;
    private String linkedInLink;

    @ManyToOne
    private User user;
//    private List<String> socialLink = new ArrayList<>();
    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();
}
