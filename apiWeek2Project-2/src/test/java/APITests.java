import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nurkulov 12/26/19
 */
public class APITests {

    @Test
    public void getGoalkeepersTest() throws IOException, URISyntaxException {
        List<String> expectedGoalkeepersList = Arrays.asList(TestConstants.getGOALKEEPERS());
        List<String> actualGoalkeepers = APITasks.getAllGoalkeepers();
        Assert.assertEquals(TestConstants.getGOALKEEPERS().length, actualGoalkeepers.size());
        Assert.assertEquals("Failed to verify goalkeepers from England league", expectedGoalkeepersList, actualGoalkeepers);
    }

    @Test
    public void getDefendersTest() throws IOException, URISyntaxException {
        List<String> acturalDefenders = APITasks.getDefenders();
        Assert.assertEquals("Failed to verify number of defenders", TestConstants.getActualDefenders().length, acturalDefenders.size());
        Assert.assertEquals("Invalid defenders list from England", Arrays.asList(TestConstants.getActualDefenders()), acturalDefenders);
    }

    @Test
    public void getMidfieldersTest() throws IOException, URISyntaxException {
        List<String> actualMidfielders = APITasks.getMidfielders();
        Assert.assertEquals("Failed to verify number of midfielders", TestConstants.getExpectedMidfielders().length, actualMidfielders.size());
        Assert.assertEquals("Invalid midfielders list from England", Arrays.asList(TestConstants.getExpectedMidfielders()), actualMidfielders);
    }

    @Test
    public void getMidfielderFromBrazil() throws IOException, URISyntaxException {
        List<String> actualMidfielderFromBrazil = APITasks.getMidfielderFromBrazil();
        Assert.assertEquals("Failed to verify number of midfielders from Brazil", TestConstants.getExpectedMidfielderFromBrazil().length, actualMidfielderFromBrazil.size());
        Assert.assertEquals("Invalid name of player from Brazil on a midfield position", Arrays.asList(TestConstants.getExpectedMidfielderFromBrazil()), actualMidfielderFromBrazil);
    }

    @Test
    public void getAttackerFromEnglandTest() throws IOException, URISyntaxException {
        List<String> actualAttackerFromEngland = APITasks.getAttackerFromEngland();
        Assert.assertEquals("Failed to verify number of attackers", TestConstants.getExpectedAttackers().length, actualAttackerFromEngland.size());
        Assert.assertEquals("Failed to verify list of attackers from England team", Arrays.asList(TestConstants.getExpectedAttackers()), actualAttackerFromEngland);
    }

    @Test
    public void getSpainCoachTest() throws IOException, URISyntaxException {
        List<String> actualCoach = APITasks.getSpainCoach();
        Assert.assertEquals(TestConstants.getExpectedCoach().length, actualCoach.size());
        Assert.assertEquals("Invalid coach name", Arrays.asList(TestConstants.getExpectedCoach()), actualCoach);
    }

    @Test
    public void getAllTeamsTest() throws IOException, URISyntaxException {
        List<String> actualTeams = APITasks.getAllTeams();
        Assert.assertEquals(TestConstants.getExpectedTeams().length, actualTeams.size());
        Assert.assertEquals(Arrays.asList(TestConstants.getExpectedTeams()), actualTeams);
    }

    @Test
    public void getSecondHighestScorer() throws IOException, URISyntaxException {

        // POJO
        List<String> actualSecondHighestScorerPojo = APITasks.getSecondHighestScorer("Pojo");
        Assert.assertEquals(TestConstants.getExpectedSecondHighestScorer().length, actualSecondHighestScorerPojo.size());
        Assert.assertEquals(Arrays.asList(TestConstants.getExpectedSecondHighestScorer()), actualSecondHighestScorerPojo);

        // TypeReference
        List<String> actualSecondHighestScorerTypeRef = APITasks.getSecondHighestScorer("TypeReference");
        Assert.assertEquals(TestConstants.getExpectedSecondHighestScorer().length, actualSecondHighestScorerTypeRef.size());
        Assert.assertEquals(Arrays.asList(TestConstants.getExpectedSecondHighestScorer()), actualSecondHighestScorerTypeRef);
    }


    @Test
    public void getAllCompetitionsTest() throws IOException, URISyntaxException {

        List<String> actualCompetitionsList = APITasks.getAllCompetitions();
        Assert.assertEquals(TestConstants.getExpectedCompetitionsList().length, actualCompetitionsList.size());
        Assert.assertEquals(Arrays.asList(TestConstants.getExpectedCompetitionsList()), actualCompetitionsList);
    }

}
