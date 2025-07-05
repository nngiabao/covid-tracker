package com.example.covid_tracker.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.covid_tracker.Model.Location;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.*;

import javax.swing.*;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;


@Service
public class DataService {
    //link to get data_url
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/refs/heads/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
    //store location stat
    private List<Location> allStats = new ArrayList<>();

    public List<Location> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(fixedRate = 1)//delay every second
    public void fecthVirusData() throws IOException, InterruptedException {
        List<Location> newStats = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        //header
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        //list
        for(CSVRecord record : records){
            Location location = new Location();
            System.out.println(record.get("Province_State"));
        }
        this.allStats = newStats;
    }
}
