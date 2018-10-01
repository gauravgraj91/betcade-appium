package com.betcade.appiumtests.core.constants;

import com.betcade.appiumtests.core.managers.ServerManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Gaurav on 5/22/2017.
 */

public class Resources {

    public static final String QUEUE = ServerManager.getWorkingDir()+"/src/main/resources/queue.json";

    public static JSONObject getQueue() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(QUEUE));
        return (JSONObject) obj;
    }
}
