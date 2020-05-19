/*
TODO: write my own class
*/
package com.steamlfg.utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.openid4java.server.ServerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Daniel Shchepetov on 07.12.2016.
 */

public class HttpClientGame {
    private HttpClient client;
    private Header contentTypeHeader;
    private String URI;

    public HttpClientGame(String URI) {
        contentTypeHeader = new BasicHeader("Content-Type", "application/json; charset=UTF-8");
        client = HttpClientBuilder.create().build();
        this.URI = URI;
    }

    public String getAll() throws ServerException {
        try {
            client = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(URI);
            get.addHeader(contentTypeHeader);

            HttpResponse response = client.execute(get);

            throwServerException(response, 200);
            return inputStreamToString(response.getEntity().getContent());


        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
    }


    private String inputStreamToString(InputStream is) {

        String line = "";
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            // Read response until the end
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {

        }

        // Return full string
        return total.toString();
    }

    private void throwServerException(HttpResponse response, int goodCode) throws ServerException {
        if (response.getStatusLine().getStatusCode() == goodCode) return;

        switch (response.getStatusLine().getStatusCode()) {
            case 404:
                throw new ServerException("Item wasn't found!");
            case 204:
                throw new ServerException("There is no content");
            case 409:
                throw new ServerException("The item already exists");
            default:
                throw new ServerException("Server error occurred!");
        }
    }

}