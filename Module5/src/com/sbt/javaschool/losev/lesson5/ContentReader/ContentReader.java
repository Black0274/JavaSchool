package com.sbt.javaschool.losev.lesson5.ContentReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class ContentReader {

    public static boolean readContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        StringBuilder response = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        while ((inputLine = reader.readLine()) != null){
            response.append(inputLine);
        }

        if (connection.getResponseCode() == 200) {
            System.out.println("Content of resource: ");
            System.out.println(response.toString());
            return true;
        }
        System.out.println("The resource is unavailable");
        return false;
    }

    public static void main(String[] args){
        System.out.println("Type URL:");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String urlString = sc.next();
            try {
                if (readContent(urlString)){
                    return;
                }
            } catch (MalformedURLException e) {
                System.out.println("Wrong URL");
            } catch (ProtocolException e){
                System.out.println("Protocol error");
            } catch (IOException e){
                System.out.println("Connection Error");
            }

            System.out.println("\nTry type another URL");
        }
    }
}
