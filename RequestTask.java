//package com.example.andreeaapplication;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import javax.net.ssl.HttpsURLConnection;
//
//class RequestTask extends AsyncTask<String, String, String> {
//
//    @Override
//    protected String doInBackground(String... uri) {
//        String responseString = null;
//        try {
//            URL url = new URL( "http://stackoverflow.com");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            if(conn.getResponseCode() == HttpsURLConnection.HTTP_OK){
//                // Do normal input or output stream reading
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        conn.getInputStream()));
//                String inputLine;
//                while ((inputLine = in.readLine()) != null)
//                    Log.i("Cferferferfer" , String.valueOf("dsfsdf"));
//
//                in.close();
//            }
//            else {
//                responseString = "FAILED"; // See documentation for more info on response handling
//            }
//        } catch (IOException e) {
//            //TODO Handle problems..
//        }
//        return responseString;
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        super.onPostExecute(result);
//        //Do anything with response..
//    }
//}