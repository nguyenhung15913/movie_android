package com.example.movieapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NetworkingService {
    private  String apiUrl = "https://api.themoviedb.org/3/movie/popular?api_key=9bd62ae862007998759f7d7a5df251bf&language=en-US&page=1";
    public static ArrayList<MovieItem> movieListManager;
    public static ExecutorService networkExecutorService = Executors.newFixedThreadPool(4);
    public static Handler networkingHandler = new Handler(Looper.getMainLooper());

    interface NetworkingListener{
        void dataListener(String jsonString);
    }

    public NetworkingListener listener;

    public void getMovieList(){
        connect(apiUrl);
    }



    public void connect(String url){

        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    String jsonData = "";
                    URL urlObj = new URL(url);
                    httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                    httpURLConnection.setRequestMethod("GET");// post, delete, put
                    httpURLConnection.setRequestProperty("Content-Type","application/json");
                    InputStream in = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    int inputSteamData = 0;
                    while ( (inputSteamData = reader.read()) != -1){// there is data in this stream
                        char current = (char)inputSteamData;
                        jsonData += current;
                    }
                    final String finalData = jsonData;
                    // the data is ready
                    networkingHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            // any code here will run in main thread
                            listener.dataListener(finalData);
                        }

                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    httpURLConnection.disconnect();
                }

            }
        });
    }
}
