package com.concretepage.recipe.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.concretepage.rating.entity.Rating;

@Entity
@Table(name="recipes")
public class Recipe {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;  
	
    private String name;
	private String category;
	
	@OneToOne
	private Detail detail;
	private Date dateCreated;
	private Date dateModified;
	
	@OneToMany(targetEntity=Rating.class,mappedBy="recipe",cascade={CascadeType.ALL},orphanRemoval=true)
	private List<Rating> ratings = new ArrayList<Rating>();
	
	public void addRating(Rating rating) {
		ratings.add(rating);
		rating.setRecipe(this);
    }
 
    public void removeRating(Rating rating) {
    	ratings.remove(rating);
    	rating.setRecipe(null);
    }
	
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

}
