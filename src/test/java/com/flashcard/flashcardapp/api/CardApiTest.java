package com.flashcard.flashcardapp.api;

import java.util.stream.Stream;
import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.flashcard.flashcardapp.FlashcardappApplication;

@SpringBootTest(classes = FlashcardappApplication.class)
@AutoConfigureMockMvc
public class CardApiTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @ParameterizedTest
    @MethodSource("addCardTestProvider")
    public void addCardTest(String requestBody, String expectedBody, String dbPath) throws Exception {
        IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        var givenUrl = this.getClass().getResource("/cards/add/" + dbPath + "/given/");
        databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
        databaseTester.onSetup();

        mockMvc.perform(
            MockMvcRequestBuilders.post("/add")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect((result) -> JSONAssert.assertEquals(
            expectedBody,
            result.getResponse().getContentAsString(),
            new CustomComparator(
                JSONCompareMode.LENIENT,
                // Exclude time_due from test
                new Customization("time_due", (o1, o2) -> true))
            ));

        var actualDataSet = databaseTester.getConnection().createDataSet();
        var actualChannelsTable = actualDataSet.getTable("channels");
        var expectedUri = this.getClass().getResource("/cards/add/" + dbPath + "/expected/");
        var expectedDataSet = new CsvURLDataSet(expectedUri);
        var expectedChannelsTable = expectedDataSet.getTable("cards");
        Assertion.assertEquals(expectedChannelsTable, actualChannelsTable);

    }

    private static Stream<Arguments> addCardTestProvider() {
        return Stream.of(
            Arguments.arguments(
                """
                    {
                        "cardFront": "add",
                        "cardBack": "追加"
                    }
                """,
                """
                    {
                        "id": 2,
                        "cardFront": "add",
                        "cardBack": "追加",
                        "reviewInterval": 1.0,
                        "correctInARow": 0,
                        "ease": 2.5
                    }
                """,
                "no-record"
            ),
            Arguments.arguments(
                """
                    {
                        "cardFront": "add",
                        "cardBack": "追加"
                    }
                """,
                """
                    {
                        "id": 2,
                        "cardFront": "add",
                        "cardBack": "追加",
                        "reviewInterval": 1.0,
                        "correctInARow": 0,
                        "ease": 2.5
                    }
                """,
                "multi-record"
            )
        );
    }

}
