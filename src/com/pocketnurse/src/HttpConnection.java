/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pocketnurse.src;

import java.io.*;
import java.net.*;


public class HttpConnection {

    public static String url;
    static String ServerReply="";
    static String serverReply() {
        return ServerReply;
    }

    /**
     *
     * @param url Url to connect
     */
    HttpConnection(String url)
    {

        this.url=url;

    }
    public void sendPost(String param)
    {
        NetworkManager nw=new NetworkManager();
        nw.doInBackground(param);
    }


    /**
     *
     */
    private void POST(String param)
    {
        HttpURLConnection urlConnection = null;
        String urlParam=param;
        try{
            URL url = new URL(HttpConnection.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Length", "" +
                    Integer.toString(param.getBytes().length));
            urlConnection.setRequestProperty("Content-Language", "en-US");
            OutputStream os = urlConnection.getOutputStream();
            os.write(urlParam.getBytes());
            //Log.i("POST","posted");
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            String text = sb.toString();
            ServerReply=text;
            System.out.println("srever response : "+text);
         //   System.out.println("connectin closed");
           // if(!text.isEmpty())
           // Log.i("post",text);
        //    else
             //   Log.i("post","EMPTY RESPONSE");
        }catch(Exception e){
           // Log.d("Exception downloading", e.toString());
            e.printStackTrace();
        }finally{

            if (urlConnection != null) urlConnection.disconnect();
        }

    }

    private class NetworkManager {


        
        protected String doInBackground(String... strings) {
            POST(strings[0]);
            return null;
        }
    }




}
