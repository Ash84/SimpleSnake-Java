package snake;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScoreDisplay {

    public static void scoreDisplay(Snake s, int i) throws Exception {

        JSONObject Score = new JSONObject();
        Score.put("Score",s.getSize());
        Score.put("Turns",i);
        Score.put("Name",s.getPlayer());
        JSONArray Scores = new JSONArray();

        if(Files.exists(Paths.get("./Scores.json"))) {
            Scores = readScores();
            JSONObject buffer;
            for (int j=Scores.size()-1; j>=0; j--) {
                buffer = (JSONObject) Scores.get(j);
                String name = (String) buffer.get("Name");
                if(name.compareTo(s.getPlayer()) == 0) {
                    Scores.remove(j);
                }
            }
            Scores.add(Score);
        }
        try {
            FileWriter ScoreWriter = new FileWriter("./Scores.json");
            ScoreWriter.write(Scores.toJSONString());
            ScoreWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON file (re)created: "+ Scores);
    }

    public static JSONArray readScores() throws Exception {
        FileReader ScoreReader = new FileReader("./Scores.json");
        JSONParser parser = new JSONParser();
        return (JSONArray) parser.parse(ScoreReader);
    }
}
