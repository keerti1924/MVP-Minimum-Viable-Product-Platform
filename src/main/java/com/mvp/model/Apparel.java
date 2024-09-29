package com.mvp.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Apparel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    private String category;  // Disposal, Donation, Recycling
    private int quantity;
    
    @Column(name = "apparel_condition")
    private String condition; // Worn out, Unused, Slightly Used
    private String status;  // Pending, Processed
    
    @Lob
    @Column(name = "image_path", columnDefinition = "LONGBLOB")
    private byte[] imagePath; // Path to the stored image

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Temporal(TemporalType.TIMESTAMP)  // This indicates it's a timestamp
    @Column(name = "posted_date")
    private Date postedDate;

    // Getters and setters

    public Long getId() {
        return id;
    }
    
	public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getImagePath() {
		return imagePath;
	}

	public void setImagePath(byte[] imagePath) {
		this.imagePath = imagePath;
	}

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

}
