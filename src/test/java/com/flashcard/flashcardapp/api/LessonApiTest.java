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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class LessonApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @ParameterizedTest
    @MethodSource("getAllLessonsTestProvider")
    public void getAllLessonsTest(String expectedBody) throws Exception {
        IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        var givenUrl = this.getClass().getResource("/lessons/getAll/given/");
        databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
        databaseTester.onSetup();

        mockMvc.perform(
            MockMvcRequestBuilders.get("/lessons/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect((result) -> JSONAssert.assertEquals(
            expectedBody,
            result.getResponse().getContentAsString(),
            false));

        var actualDataSet = databaseTester.getConnection().createDataSet();
        var actualLessonsTable = actualDataSet.getTable("lessons");
        var expectedUri = this.getClass().getResource("/lessons/getAll/given/");
        var expectedDataSet = new CsvURLDataSet(expectedUri);
        var expectedLessonsTable = expectedDataSet.getTable("lessons");
        Assertion.assertEquals(expectedLessonsTable, actualLessonsTable);

    }

    // TODO Lesson requested doesn't exist, lesson requested has no slides
    private static Stream<Arguments> getAllLessonsTestProvider() {
        return Stream.of(
            Arguments.arguments(
                """
                    [
                        {
                            "lessonId": 1,
                            "title": "Test Lesson",
                            "description": "Description of test lesson"
                        }
                    ]
                """
            )
        );
    }

    @ParameterizedTest
    @MethodSource("getSpecificLessonTestProvider")
    public void getSpecificLessonTest(String lessonId, String expectedBody) throws Exception {
        IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        var givenUrl = this.getClass().getResource("/lessons/getOne/given/");
        databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
        databaseTester.onSetup();

        mockMvc.perform(
            MockMvcRequestBuilders.get("/lessons/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect((result) -> JSONAssert.assertEquals(
            expectedBody,
            result.getResponse().getContentAsString(),
            false));

        var actualDataSet = databaseTester.getConnection().createDataSet();
        var actualLessonsTable = actualDataSet.getTable("lessons");
        var expectedUri = this.getClass().getResource("/lessons/getOne/given/");
        var expectedDataSet = new CsvURLDataSet(expectedUri);
        var expectedLessonsTable = expectedDataSet.getTable("lessons");
        Assertion.assertEquals(expectedLessonsTable, actualLessonsTable);
    }

    private static Stream<Arguments> getSpecificLessonTestProvider() {
        return Stream.of(
            Arguments.arguments(
                """
                    1
                """,
                """
                    {
                    
                        "lesson": {
                            "lessonId": 1,
                            "title": "Test Lesson",
                            "description": "Description of test lesson"
                        },
                        "slides": [
                            {
                                "slideId": 1,
                                "lessonId": 1,
                                "text": "testing add slide",
                                "imageLoc": "12345.jpg",
                                "audioLoc": "12345.wav"
                            },
                            {
                                "slideId": 2,
                                "lessonId": 1,
                                "text": "second slide added",
                                "imageLoc": "123987.jpg",
                                "audioLoc": "123987.wav"
                            }
                        ]
                    }
                """
            )
        );
    }
    
}
