package org.example.ui;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.example.utils.models.RandomDataModel;
import org.example.utils.constants.SingletonInstance;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataGetter {
    private int num;
    private String format;
    private ObjectMapper objectMapper;


    // constructor to get the input and format that the user want
    public DataGetter(int num, String format) {
        SingletonInstance singletonInstance = SingletonInstance.getInstance();
        objectMapper = singletonInstance.getObjectMapper();
        this.num = num;
        this.format = format;

    }

    // class to get the data
    // I choose to use jackson to convert from json to object, write object to json file, and write object to csv file because of it's simplicity
    public void getData() throws IOException, InterruptedException {
        int i = 0;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://bored-api.appbrewery.com/random"))
                .build();
        switch (format) {
            case "console":
                while (i < num) {
                    HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                    // error handling
                    if (response.body().equals("Too many requests, please try again later.")) {
                        System.out.println("Connection Time out!");
                        return;
                    }
                    RandomDataModel model = objectMapper.readValue(response.body(), RandomDataModel.class);
                    System.out.println(model);
                    i++;
                }
                return;

            case "json":
                while (i < num) {
                    HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                    // error handling
                    if (response.body().equals("Too many requests, please try again later.")) {
                        System.out.println("Connection Time out!");
                        return;
                    }
                    RandomDataModel model = objectMapper.readValue(response.body(), RandomDataModel.class);

                    String fileName = "test.json";
                    File jsonFile = new File(fileName);
                    int tempCounter = 0;
                    while(jsonFile.exists()) {
                        tempCounter++;
                        fileName = "test(" + tempCounter + ").json";
                        jsonFile = new File(fileName);
                    }

                    objectMapper.writeValue(jsonFile, model);
                    System.out.println("JSON file downloaded in: " + jsonFile.getAbsoluteFile());
                    i++;
                }
                return;

            case "csv":
                while (i < num) {
                    HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                    // error handling
                    if (response.body().equals("Too many requests, please try again later.")) {
                        System.out.println("Connection Time out!");
                        return;
                    }
                    RandomDataModel model = objectMapper.readValue(response.body(), RandomDataModel.class);
                    CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();

                    JsonNode jsonTree = objectMapper.valueToTree(model);

                    jsonTree.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);
                    CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
                    CsvMapper csvMapper = new CsvMapper();

                    String fileName = "test.csv";
                    File csvFile = new File(fileName);
                    int tempCounter = 0;
                    while(csvFile.exists()) {
                        tempCounter++;
                        fileName = "test(" + tempCounter + ").csv";
                        csvFile = new File(fileName);
                    }

                    csvMapper.writerFor(JsonNode.class)
                            .with(csvSchema)
                            .writeValue(csvFile, jsonTree);
                    System.out.println("CSV file downloaded in: " + csvFile.getAbsoluteFile());
                    i++;
                }
                return;

            default:
                System.out.println("Invalid input!");
        }
    }
}
