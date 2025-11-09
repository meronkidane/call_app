package com.streamhub.common.domain;

import java.util.List;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.streamhub.common.mongo.BaseDocument;

@Document(collection = "titles")
@CompoundIndex(name = "idx_titles_genres_tags", def = "{ 'genres': 1, 'tags': 1 }")
public class Title extends BaseDocument {

    private String type; // MOVIE or SERIES

    @TextIndexed
    private String name;

    private String synopsis;

    private int year;

    @TextIndexed
    private List<String> genres;

    private List<String> cast;

    @TextIndexed
    private List<String> tags;

    private double rating;

    private String artwork;

    private List<String> availabilityRegions;

    private boolean premium;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getArtwork() {
        return artwork;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    public List<String> getAvailabilityRegions() {
        return availabilityRegions;
    }

    public void setAvailabilityRegions(List<String> availabilityRegions) {
        this.availabilityRegions = availabilityRegions;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
