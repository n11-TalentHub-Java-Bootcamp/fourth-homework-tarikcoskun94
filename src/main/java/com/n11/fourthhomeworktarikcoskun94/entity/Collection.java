package com.n11.fourthhomeworktarikcoskun94.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "collections")
public class Collection implements Serializable {

    @SequenceGenerator(name = "generator", sequenceName = "collection_id_seq", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}