package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String json = readString("data.json");
        List<Employee> list = jsonToList(json);
        System.out.println(list);
    }

    public static List<Employee> jsonToList(String json) {

        List<Employee> employeeList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(json);
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            for (int i = 0; i < jsonArray.size(); i++) {
                Employee employee = gson.fromJson(jsonArray.get(i).toString(), Employee.class);
                employeeList.add(employee);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    public static String readString(String filename) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(filename);
        String jsonString;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder rawJson = new StringBuilder();
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                rawJson.append(readLine);
            }
            jsonString = rawJson.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

}

