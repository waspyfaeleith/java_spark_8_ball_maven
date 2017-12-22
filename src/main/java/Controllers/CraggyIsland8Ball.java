package Controllers;
import APIConnection.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class CraggyIsland8Ball {
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/main.vtl" );
            return new ModelAndView(model, "templates/layout.vtl");
        },new VelocityTemplateEngine());

        get("/answer", (req, res) -> {
            APIConnection connection = new APIConnection();
            Answer answer = connection.getRandomAnswer();
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/answer.vtl" );
            model.put("answer", answer);
            return new ModelAndView(model, "templates/layout.vtl");
        },new VelocityTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
