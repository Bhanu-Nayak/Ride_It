package com.rideit.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="photobystring")
public class PhotosByString {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageId;


	@Column(name="name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bike_id")
    private Bike bike;
	
	private String type;
	private String filepath;
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Bike getBike() {
		return bike;
	}
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public PhotosByString() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhotosByString(Long imageId, String name, Bike bike, String type, String filepath) {
		super();
		this.imageId = imageId;
		this.name = name;
		this.bike = bike;
		this.type = type;
		this.filepath = filepath;
	}
	@Override
	public String toString() {
		return "PhotosByString [imageId=" + imageId + ", name=" + name + "type=" + type
				+ ", filepath=" + filepath + "]";
	}
	
	

}
