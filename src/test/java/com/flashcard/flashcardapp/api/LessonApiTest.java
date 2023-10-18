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

    }

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
    
}
