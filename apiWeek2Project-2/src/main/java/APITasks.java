import MidFielderFromBrazil.MidfielderFromBrazilPogo;
import MidFielderFromBrazil.Squad;
import SecondHighestScorer.Scorers;
import SecondHighestScorer.SecondHighestScorerPojo;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nurkulov 12/26/19
 */
public class APITasks {

    private static HttpClient httpClient;
    private static URIBuilder uri;
    private static HttpGet httpGet;
    private static HttpResponse response;
    private static ObjectMapper objectMapper;

    public APITasks() throws IOException {
    }

    /*
     * GET all soccer team names listed in given resource
     * Deserialization type: Pojo
     */
    public static List<String> getAllTeams() throws URISyntaxException, IOException {
        return null;
    }

    /*
     * GET names of all goalkeepers from England team
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getAllGoalkeepers() throws URISyntaxException, IOException {

        return null;
    }

    /*
     * GET names of all defenders from England team
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getDefenders() throws URISyntaxException, IOException {
        return null;
    }

    /*
     * GET names of all midfielders from England team
     * note: England team id is 66
     * Deserialization type: Pojo
     */
    public static List<String> getMidfielders() throws IOException, URISyntaxException {
        return null;
    }

    /*
     * GET names of all midfielders from England team whose country is Brazil
     * note: England team id is 66
     * Deserialization type: Pojo
     */
    public static List<String> getMidfielderFromBrazil() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();

        uri = new URIBuilder();
        uri.setScheme("http").setHost("api.football-data.org").setPath("v2/teams/66");

        httpGet = new HttpGet(uri.build());
        httpGet.setHeader("X-Auth-Token", "18aae6ea28fc40e08a4b9a8f0f6074af");

        response = httpClient.execute(httpGet);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertTrue(response.getEntity().getContentType().getValue().startsWith("application/json"));

        objectMapper = new ObjectMapper();
        MidfielderFromBrazilPogo midfieldBrazil = objectMapper.readValue(response.getEntity().getContent(), MidfielderFromBrazilPogo.class);

        Squad[] squadArray = midfieldBrazil.getSquad();
        List<String> actualMidfielderFromBrazil = new ArrayList<>();
        for (Squad squad : squadArray) {
            if (squad.getCountryOfBirth().equalsIgnoreCase("Brazil")) {
                actualMidfielderFromBrazil.add(squad.getName());
            }
        }
        return actualMidfielderFromBrazil;
    }


    /*
     * GET names of all attackers from England team whose origin country is England
     * note: England team id is 66
     * Deserialization type: Pojo
     */
    public static List<String> getAttackerFromEngland() throws URISyntaxException, IOException {
        return null;
    }

    /*
     * GET name of Spain team coach
     * note: Spain team id is 77
     * Deserialization type: Pojo
     */
    public static List<String> getSpainCoach() throws URISyntaxException, IOException {
        return null;
    }


    public static List<String> getAllCompetitions() throws URISyntaxException, IOException {
        return null;

    }


    /*
     * GET names of second highest scorer from competitions of 2000 season
     * note: endpoint for competitions: `competitions/<year>/
     * note: endpoint for scorers: `competitions/<year>/scorers`
     * Deserialization type: Pojo and TypeReference
     */
    public static List<String> getSecondHighestScorer(String deserializationType) throws URISyntaxException, IOException {

        httpClient = HttpClientBuilder.create().build();

        uri = new URIBuilder();
        uri.setScheme("http").setHost("api.football-data.org").setPath("v2/competitions/2000/scorers");

        httpGet = new HttpGet(uri.build());
        httpGet.setHeader("X-Auth-Token", "18aae6ea28fc40e08a4b9a8f0f6074af");

        response = httpClient.execute(httpGet);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertTrue(response.getEntity().getContentType().getValue().startsWith("application/json"));

        objectMapper = new ObjectMapper();


    // POJO
        if (deserializationType.equalsIgnoreCase("Pojo")) {
            SecondHighestScorerPojo secondHighScorerPojo = objectMapper.readValue(response.getEntity().getContent(), SecondHighestScorerPojo.class);
            Scorers[] scorersArray = secondHighScorerPojo.getScorers();

            List<String> actualSecondHighestScorer = new ArrayList<>();
            List<Integer> numbersOfGoals = new ArrayList<>();

            for (Scorers scorer : scorersArray) {
                numbersOfGoals.add(scorer.getNumberOfGoals());

                if (scorer.getNumberOfGoals() == secondHighest(numbersOfGoals)) {
                    actualSecondHighestScorer.add(scorer.getPlayer().getName());
                }
            }
            return actualSecondHighestScorer;


// TypeReference
        } else if (deserializationType.equalsIgnoreCase("TypeReference")) {

            Map<String, Object> deserializedObject = objectMapper.readValue(response.getEntity().getContent(),
                    new TypeReference<Map<String, Object>>() {
                    });

            List<Map<String, Object>> scorers = (List) deserializedObject.get("scorers");
            List<Integer> numbersOfGoalsList = new ArrayList<>();
            List<String> actualSecondHighestScorerTypeRef = new ArrayList<>();

            for (Map<String, Object> scorer : scorers) {
                numbersOfGoalsList.add((int) scorer.get("numberOfGoals"));

                Map<String, Object> players = (Map<String, Object>) scorer.get("player");

                if ((int) scorer.get("numberOfGoals") == secondHighest(numbersOfGoalsList)) {
                    actualSecondHighestScorerTypeRef.add((String) players.get("name"));
                }
            }
            return actualSecondHighestScorerTypeRef;
        }
        return null;
    }


    static int secondHighest(List<Integer> numsOfGoals) {
        int highest = 0;
        int secondHighest = 0;

        for (int num : numsOfGoals) {
            if (num > highest) {
                secondHighest = highest;
                highest = num;
            } else if (num > secondHighest) {
                secondHighest = num;
            }
        }
        return secondHighest;
    }
}

