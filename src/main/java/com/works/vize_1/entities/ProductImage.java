package com.works.vize_1.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    private Long pid;

    @Lob
    private Blob image;


}
