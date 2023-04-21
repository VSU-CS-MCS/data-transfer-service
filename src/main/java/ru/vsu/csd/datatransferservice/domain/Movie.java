package ru.vsu.csd.datatransferservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vsu.csd.datatransferservice.exception.IncorrectArrayLengthException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

@Entity
@Table
@ToString(of = {"id", "title"})
@EqualsAndHashCode(of = "id")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String yearRelease;
    @Column(name = "review", length = -1)
    private String review;
    private double emotionalColoringJoy;
    private double emotionalColoringSadness;
    private double emotionalColoringAnger;
    private double emotionalColoringDisgust;
    private double emotionalColoringFear;

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @JsonIgnore
    public double[] getEmotional() {
        return new double[]{
                emotionalColoringJoy,
                emotionalColoringSadness,
                emotionalColoringAnger,
                emotionalColoringDisgust,
                emotionalColoringFear};
    }

    public void setEmotionalColoring(ArrayList<Double> emotionalColoring) {
        if (emotionalColoring.size() != 5) {
            throw new IncorrectArrayLengthException();
        }

        emotionalColoringJoy = emotionalColoring.get(0);
        emotionalColoringSadness = emotionalColoring.get(1);
        emotionalColoringAnger = emotionalColoring.get(2);
        emotionalColoringDisgust = emotionalColoring.get(3);
        emotionalColoringFear = emotionalColoring.get(4);
    }

    public ArrayList<Double> analysisEmotionalColoring(String review) {
        ArrayList<Double> result = new ArrayList<Double>();

        try {
            URL url = new URL("http://localhost:9000/text-for-analysis");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // set request method and headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // set request body
            String requestBody = review;
            connection.setDoOutput(true);
            connection.getOutputStream().write(requestBody.getBytes());

            // read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            for (String stringResult :
                    response.toString().substring(1, response.toString().length()-1).split(",")) {
                result.add(Double.parseDouble(stringResult));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
