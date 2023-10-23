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
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class PreferencesApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @ParameterizedTest
    @MethodSource("getPreferencesTestProvider")
    public void getPreferencesTest(String expectedBody) throws Exception {
        IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        var givenUrl = this.getClass().getResource("/preferences/get/given/");
        databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
        databaseTester.onSetup();

        mockMvc.perform(
            MockMvcRequestBuilders.get("/preferences/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect((result) -> JSONAssert.assertEquals(
            expectedBody,
            result.getResponse().getContentAsString(),
            false));

        var actualDataSet = databaseTester.getConnection().createDataSet();
        var actualLessonsTable = actualDataSet.getTable("preferences");
        var expectedUri = this.getClass().getResource("/preferences/get/given/");
        var expectedDataSet = new CsvURLDataSet(expectedUri);
        var expectedLessonsTable = expectedDataSet.getTable("preferences");
        Assertion.assertEquals(expectedLessonsTable, actualLessonsTable);
    }

    private static Stream<Arguments> getPreferencesTestProvider() {
        return Stream.of(
            Arguments.arguments(
                """
                        {
                            "interfaceLanguage": "English",
                            "volume": 4,
                            "audioOn": true,
                            "hintsOn": true,
                            "romajiOn": true,
                            "darkMode": true
                        }
                """
            )
        );
    }

    @ParameterizedTest
    @MethodSource("changePreferencesTestProvider")
    public void changePreferencesTest(String requestBody, String dbPath) throws Exception {
        IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        var givenUrl = this.getClass().getResource("/preferences/change/" + dbPath + "/given/");
        databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
        databaseTester.onSetup();


        mockMvc.perform(
            MockMvcRequestBuilders.put("/preferences/")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

        var actualDataSet = databaseTester.getConnection().createDataSet();
        var actualLessonsTable = actualDataSet.getTable("preferences");
        var expectedUri = this.getClass().getResource("/preferences/change/" + dbPath + "/expected/");
        var expectedDataSet = new CsvURLDataSet(expectedUri);
        var expectedLessonsTable = expectedDataSet.getTable("preferences");
        Assertion.assertEquals(expectedLessonsTable, actualLessonsTable);
    }

    private static Stream<Arguments> changePreferencesTestProvider() {
        return Stream.of(
            Arguments.arguments(
                """
                    {
                        "userId": 1,
                        "interfaceLanguage": "Japanese",
                        "volume": 8,
                        "audioOn": false,
                        "hintsOn": false,
                        "romajiOn": false,
                        "darkMode": false
                    }
                """,
                "valid"
            ),
            Arguments.arguments(
                """
                    {
                        "userId": 1,
                        "interfaceLanguage": "English",
                        "volume": 4,
                        "audioOn": true,
                        "hintsOn": 7,
                        "romajiOn": true,
                        "darkMode": true
                    }
                """,
                "invalid"
            )
        );
    }

}
