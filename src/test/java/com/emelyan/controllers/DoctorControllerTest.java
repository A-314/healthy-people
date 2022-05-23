package com.emelyan.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.Assert.assertEquals;


class DoctorControllerTest {
    @Test
    void getAll() throws IOException {
        URL url = new URL("http://localhost:8080");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        assertEquals(200, responseCode);
        connection.disconnect();
    }

    @Test
    void edit() throws IOException{
        URL url = new URL("http://localhost:8080/152/edit");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        assertEquals(200, responseCode);
        connection.disconnect();
    }

    @Test
    @Disabled("Fix later")
    void update() throws IOException{
        URL url = new URL("http://localhost:8080/152");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("PATCH");

        connection.getInputStream();
        int responseCode = connection.getResponseCode();

        assertEquals(200, responseCode);
        connection.disconnect();
    }
    @Test
    @Disabled("Fix later")
    void createNew() throws IOException {
        URL url = new URL("http://localhost:8080/new");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        assertEquals(200, responseCode);
        connection.disconnect();
    }
    @Test
    void saveNew() throws IOException {
        URL url = new URL("http://localhost:8080/");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        int responseCode = connection.getResponseCode();

        assertEquals(200, responseCode);
    }
    @Test
    @Disabled("Fix later")
    void delete() throws IOException {
        URL url = new URL("http://localhost:8080/152");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty(
                "Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestMethod("POST");
        int responseCode = connection.getResponseCode();

        assertEquals(200, responseCode);

    }
}