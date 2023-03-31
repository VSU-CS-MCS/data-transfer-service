package ru.vsu.csd.datatransferservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String yearRelease;
    private String review;
    private double emotionalColoringJoy;
    private double emotionalColoringSadness;
    private double emotionalColoringAnger;
    private double emotionalColoringDisgust;
    private double emotionalColoringFear;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(String yearRelease) {
        this.yearRelease = yearRelease;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getEmotionalColoringJoy() {
        return emotionalColoringJoy;
    }

    public void setEmotionalColoringJoy(double emotionalColoringJoy) {
        this.emotionalColoringJoy = emotionalColoringJoy;
    }

    public double getEmotionalColoringSadness() {
        return emotionalColoringSadness;
    }

    public void setEmotionalColoringSadness(double emotionalColoringSadness) {
        this.emotionalColoringSadness = emotionalColoringSadness;
    }

    public double getEmotionalColoringAnger() {
        return emotionalColoringAnger;
    }

    public void setEmotionalColoringAnger(double emotionalColoringAnger) {
        this.emotionalColoringAnger = emotionalColoringAnger;
    }

    public double getEmotionalColoringDisgust() {
        return emotionalColoringDisgust;
    }

    public void setEmotionalColoringDisgust(double emotionalColoringDisgust) {
        this.emotionalColoringDisgust = emotionalColoringDisgust;
    }

    public double getEmotionalColoringFear() {
        return emotionalColoringFear;
    }

    public void setEmotionalColoringFear(double emotionalColoringFear) {
        this.emotionalColoringFear = emotionalColoringFear;
    }

    public double[] getEmotional() {
        return new double[] {
                emotionalColoringJoy,
                emotionalColoringSadness,
                emotionalColoringAnger,
                emotionalColoringDisgust,
                emotionalColoringFear};
    }

    public void setEmotionalColoring(double[] emotionalColoring) throws Exception {
        if (emotionalColoring.length != 5) {
            throw new Exception("incorrect number of elements in the array");
        }

        emotionalColoringJoy = emotionalColoring[0];
        emotionalColoringSadness = emotionalColoring[1];
        emotionalColoringAnger = emotionalColoring[2];
        emotionalColoringDisgust = emotionalColoring[3];
        emotionalColoringFear = emotionalColoring[4];
    }
}
