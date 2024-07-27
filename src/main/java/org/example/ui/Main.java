package org.example.ui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        // Class utility to get the data from the API
        // first argument is the number of data you want to get
        // second argument is the type of data you want it to get sample (csv, json, console)
        DataGetter dataGetter = new DataGetter(1, "csv");
        dataGetter.getData();
    }
}