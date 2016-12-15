package Game;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private ArrayList<Results> results;

    /**
     * Constructor without name, name will then be set to "Unknown".
     */
    public Player() {
        this.name = "Unknown";
        results = new ArrayList<>();
    }

    /**
     * Constructor with name
     * @param name, name to use
     */
    public Player(String name){
        this.name = name;
        results = new ArrayList<>();
    }

    /**
     * Setter for name
     * @param name, the new name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Getter for the name
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Adds results to the resultlist.
     * @param r the results.
     */
    public void setResults(Results r) {
        results.add(r);

    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public boolean sendResult(Results result) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("https://powerful-lowlands-13870.herokuapp.com/leader_boards");

// Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<>(2);
//        params.add(new BasicNameValuePair("player_name", "test"));
//        params.add(new BasicNameValuePair("level_name", levelName));
//        params.add(new BasicNameValuePair("credits_left", String.valueOf(creditsLeft)));
//        params.add(new BasicNameValuePair("time_played", time.toString()));
        params.add(new BasicNameValuePair("leader_board", "{ player_name\": \"" + name + "\", " +
                "\"level_name\": \"" + result.getLevelName() +
                "\", \"credits_left\": \"" + result.getCreditsLeft() +
                "\", \"time_played\": \"" + result.getTime() + "\"}"));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//        httppost.addHeader("player_name", "test");
//        httppost.addHeader("level_name", levelName);
//        httppost.addHeader("credits_left", String.valueOf(creditsLeft));
//        httppost.addHeader("time_played", time.toString());

        httppost.addHeader("leader_board", "{ player_name\": \"" + name + "\", " +
                "\"level_name\": \"" + result.getLevelName() +
                "\", \"credits_left\": \"" + result.getCreditsLeft() +
                "\", \"time_played\": \"" + result.getTime() + "\"}");


        System.out.println(httppost.getAllHeaders()[0].getValue());

//Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        //TODO print
        System.out.println("Server response\n" + response.getStatusLine().getReasonPhrase() + "\n" +
                response.getStatusLine().getStatusCode());

        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                // do something useful
            } finally {
                instream.close();
            }
        }
        return false;
    }
}