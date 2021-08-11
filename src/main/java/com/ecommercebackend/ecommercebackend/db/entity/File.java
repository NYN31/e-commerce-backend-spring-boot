package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class File {
    @Id
    @Column(name = "gpx_id")
    public Integer gpxId;
    @Column(name = "file_name")
    public String fileName;
    @Column(name = "ver")
    public Double ver;
    @Column(name = "creator")
    public String creator;
}
