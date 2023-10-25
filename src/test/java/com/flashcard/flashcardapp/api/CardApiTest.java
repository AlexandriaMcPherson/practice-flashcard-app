package com.flashcard.flashcardapp.api;

import java.util.stream.Stream;
import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
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

    // TODO Card added is not valid, card added already exists (check if front in deck before saving)
    @ParameterizedTest
    @MethodSource("addCardTestProvider")
    public void addCardTest(String requestBody, String expectedBody, String dbPath, int httpStatus) throws Exception {
        IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        var givenUrl = this.getClass().getResource("/cards/add/" + dbPath + "/given/");
        databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
        databaseTester.onSetup();

        mockMvc.perform(
            MockMvcRequestBuilders.post("/cards/add")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            )
        .andExpect(MockMvcResultMatchers.status().is(httpStatus))
        .andExpect((result) -> JSONAssert.assertEquals(
            expectedBody,
            result.getResponse().getContentAsString(),
            new CustomComparator(
                JSONCompareMode.LENIENT,
                // timestampは検証対象外とする(どんな値でもOK)
                new Customization("timeDue", (o1, o2) -> true))
        ));

        String[] sortBy = {"id", "card_front", "card_back", "notes", "review_interval", "correct_in_a_row", "ease"};
        String[] excludeColumns = {"time_due"};

        var actualDataSet = databaseTester.getConnection().createDataSet();
        var actualCardsTable = new SortedTable(
      DefaultColumnFilter.excludedColumnsTable(
            actualDataSet.getTable("cards"),
            excludeColumns
            ), sortBy
        );
        var expectedUri = this.getClass().getResource("/cards/add/" + dbPath + "/expected/");
        var expectedDataSet = new CsvURLDataSet(expectedUri);
        var expectedCardsTable = new SortedTable(
            DefaultColumnFilter.excludedColumnsTable(
              expectedDataSet.getTable("cards"),
              excludeColumns
            ), sortBy);
        Assertion.assertEquals(expectedCardsTable, actualCardsTable);

    }

    private static Stream<Arguments> addCardTestProvider() {
        return Stream.of(
            Arguments.arguments(
                """
                    {
                        "cardFront": "add",
                        "cardBack": "追加",
                        "notes": "note to self"
                    }
                """,
                """
                    {
                        "id": 2,
                        "cardFront": "add",
                        "cardBack": "追加",
                        "notes": "note to self",
                        "reviewInterval": 1.0,
                        "correctInARow": 0,
                        "ease": 2.5
                    }
                """,
                "no-record",
                200
            ),
            Arguments.arguments(
                """
                    {
                        "cardFront": "add",
                        "cardBack": "追加",
                        "notes": "note to self"
                    }
                """,
                """
                    {
                        "id": 2,
                        "cardFront": "add",
                        "cardBack": "追加",
                        "notes": "note to self",
                        "reviewInterval": 1.0,
                        "correctInARow": 0,
                        "ease": 2.5
                    }
                """,
                "multi-record",
                200
            ),
            Arguments.arguments(
                """
                    {
                        "cardFront": "start",
                        "cardBack": "開始",
                        "notes": "note to self"
                    }
                """,
                """
                    {
                        "status": 409,
                        "error": "409 CONFLICT \\\"Card with specified front already exists.\\\""
                    }
                """,
                "conflict",
                409
            )
        );
    }

    // TODO Case when no cards exist, case when extremely large number of cards exist
    @ParameterizedTest
    @MethodSource("browseCardsTestProvider")
    public void browseCardsTest(String expectedBody) throws Exception {
        IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        var givenUrl = this.getClass().getResource("/cards/browse/given/");
        databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
        databaseTester.onSetup();

        mockMvc.perform(
            MockMvcRequestBuilders.get("/cards/browse"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect((result) -> JSONAssert.assertEquals(
            expectedBody,
            result.getResponse().getContentAsString(),
            new CustomComparator(
                JSONCompareMode.LENIENT,
                // Exclude time_due from test
                new Customization("timeDue", (o1, o2) -> true))
                ));

        var actualDataSet = databaseTester.getConnection().createDataSet();
        var actualCardsTable = actualDataSet.getTable("cards");
        var expectedUri = this.getClass().getResource("/cards/browse/given/");
        var expectedDataSet = new CsvURLDataSet(expectedUri);
        var expectedCardsTable = expectedDataSet.getTable("cards");
        Assertion.assertEquals(expectedCardsTable, actualCardsTable);

    }

    private static Stream<Arguments> browseCardsTestProvider() {
        return Stream.of(
            Arguments.arguments(
                """
                    [
                        {
                            "id": 1,
                            "cardFront": "start",
                            "cardBack": "開始",
                            "notes": "note to self",
                            "reviewInterval": 1.0,
                            "correctInARow": 0,
                            "ease": 2.5
                        }
                    ]
                """
            )
        );
    }

    // TODO Case when no cards are due, case when extremely large number of cards are due
    @ParameterizedTest
    @MethodSource("studyCardsTestProvider")
    public void studyCardsTest(String expectedBody) throws Exception {
        IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        var givenUrl = this.getClass().getResource("/cards/study/given/");
        databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
        databaseTester.onSetup();

        mockMvc.perform(
            MockMvcRequestBuilders.get("/cards/study"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect((result) -> JSONAssert.assertEquals(
            expectedBody,
            result.getResponse().getContentAsString(),
            new CustomComparator(
                JSONCompareMode.LENIENT,
                // Exclude time_due from test
                new Customization("timeDue", (o1, o2) -> true))
                ));

        var actualDataSet = databaseTester.getConnection().createDataSet();
        var actualCardsTable = actualDataSet.getTable("cards");
        var expectedUri = this.getClass().getResource("/cards/study/given/");
        var expectedDataSet = new CsvURLDataSet(expectedUri);
        var expectedCardsTable = expectedDataSet.getTable("cards");
        Assertion.assertEquals(expectedCardsTable, actualCardsTable);

    }

    private static Stream<Arguments> studyCardsTestProvider() {
    return Stream.of(
        Arguments.arguments(
            """
                [
                    {
                        "id": 1,
                        "cardFront": "start",
                        "cardBack": "開始",
                        "notes": "note to self",
                        "reviewInterval": 1.0,
                        "correctInARow": 0,
                        "ease": 2.5
                    },
                    {
                        "id": 2,
                        "cardFront": "add",
                        "cardBack": "追加",
                        "notes": "note to self",
                        "reviewInterval": 1.0,
                        "correctInARow": 0,
                        "ease": 2.5
                    }
                ]
            """
        )
    );
}

}
