package persistence;

import model.MyCart;
import model.Ticket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads my cart from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads my cart from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MyCart read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMyCart(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses my cart from JSON object and returns it
    private MyCart parseMyCart(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MyCart mc = new MyCart(name);
        addTickets(mc, jsonObject);
        return mc;
    }

    // MODIFIES: mc
    // EFFECTS: parses tickets from JSON object and adds them to my cart
    private void addTickets(MyCart mc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tickets");
        for (Object json : jsonArray) {
            JSONObject nextTicket = (JSONObject) json;
            addTicket(mc, nextTicket);
        }
    }

    // MODIFIES: mc
    // EFFECTS: parses tickets from JSON object and adds it to my cart
    private void addTicket(MyCart mc, JSONObject jsonObject) {
        String seatLevel = jsonObject.getString("level");
        int seatSection = jsonObject.getInt("section");
        int seatRow = jsonObject.getInt("row");
        int seatNumber = jsonObject.getInt("number");
        double seatPrice = jsonObject.getDouble("price");
        Ticket ticket = new Ticket(seatLevel, seatSection, seatRow, seatNumber, seatPrice);
        mc.addTicket(ticket);
    }
}
// Methods above were taken from JsonReader in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git