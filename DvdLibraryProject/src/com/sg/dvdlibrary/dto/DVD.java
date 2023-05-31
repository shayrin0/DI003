package com.sg.dvdlibrary.dto;

import java.util.Objects;

/**
 * This is the DTO that holds all the DVD's info
 */

public class DVD {

    private String title;
    private String releaseDate;
    private String ratingMPAA;
    private String directorName;
    private String studio;
    private String userRating;

    public DVD(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRatingMPAA() {
        return ratingMPAA;
    }

    public void setRatingMPAA(String ratingMPAA) {
        this.ratingMPAA = ratingMPAA;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DVD dvd = (DVD) o;
        return Objects.equals(title, dvd.title)
                && Objects.equals(releaseDate, dvd.releaseDate)
                && Objects.equals(ratingMPAA, dvd.ratingMPAA)
                && Objects.equals(directorName, dvd.directorName)
                && Objects.equals(studio, dvd.studio)
                && Objects.equals(userRating, dvd.userRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate, ratingMPAA, directorName, studio, userRating);
    }

    @Override
    public String toString() {
        return "DVD{" +
                "title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", ratingMPAA='" + ratingMPAA + '\'' +
                ", directorName='" + directorName + '\'' +
                ", studio='" + studio + '\'' +
                ", userRating='" + userRating + '\'' +
                '}';
    }
}
