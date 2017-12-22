package APIConnection;

import java.util.ArrayList;
import java.util.Collections;

public  class AnswerList {
    public ArrayList<Answer> answers;

    public Answer getRandomAnswer() {
        Collections.shuffle(answers);
        return answers.get(0);
    }
}
