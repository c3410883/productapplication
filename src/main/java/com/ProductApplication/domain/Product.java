package com.ProductApplication.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ProductEntry {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @NotEmpty
    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @NotEmpty
    private String price;
    public String getPrice() {return price;}
    public void setPrice(String price) {this.price = price;}

    @NotEmpty
    private String description;
    public String getDescription() {return description;}
    public void setDescription(String Description) {this.description = Description;}

    @NotEmpty
    private String comment;
    public String getComment() { return comment;}
    public void setComment(String comment) {this.comment = comment;}

    @CreatedDate
    @Column(nullable = false)
    private Date created;
    public Date getCreated() {return created;}
    public void setCreated(Date created) {this.created = created;}

    @LastModifiedDate
    @Column(nullable = false)
    private Date lastUpdated;
    public Date getLastUpdated() {return lastUpdated;}
    public void setLastUpdated(Date lastUpdated) {this.lastUpdated = lastUpdated;}
}
