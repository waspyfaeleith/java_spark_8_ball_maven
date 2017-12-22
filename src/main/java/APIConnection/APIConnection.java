package APIConnection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIConnection {

    private AnswerList answersFromAPI;

    public Answer getRandomAnswer() {
        try {
            this.getAnswersFromApi();
        }
        catch (IOException ex) {

        }
        Answer randomAnswer = this.answersFromAPI.getRandomAnswer();
        return randomAnswer;
    }

    private void getAnswersFromApi() throws IOException {
        URL url = new URL("https://craggy-island-8-ball-api.herokuapp.com/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        conn.connect();

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        Gson gson = new Gson();

        String answersFromAPI = "";
        Scanner sc = new Scanner(url.openStream());

        while (sc.hasNext()) {
            String answer = sc.nextLine();
            answersFromAPI += answer;
        }
        sc.close();

        TypeToken<AnswerList> answerList = new TypeToken<AnswerList>() {};
        this.answersFromAPI = gson.fromJson(answersFromAPI, answerList.getType());
    }
}
