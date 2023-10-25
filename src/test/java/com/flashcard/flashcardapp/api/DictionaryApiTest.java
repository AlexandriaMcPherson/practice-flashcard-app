package com.flashcard.flashcardapp.api;

import java.util.stream.Stream;

import javax.sql.DataSource;

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

@SpringBootTest
@AutoConfigureMockMvc
public class DictionaryApiTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @ParameterizedTest
    @MethodSource("browseDictionaryTestProvider")
    public void browseDictionaryTest(String expectedBody) throws Exception {
        IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        var givenUrl = this.getClass().getResource("/dictionary/browse/given/");
        databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
        databaseTester.onSetup();

        mockMvc.perform(
            MockMvcRequestBuilders.get("/dictionary/browse"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect((result) -> JSONAssert.assertEquals(
            expectedBody,
            result.getResponse().getContentAsString(),
            false));
    }

    private static Stream<Arguments> browseDictionaryTestProvider() {
        return Stream.of(
            Arguments.arguments(
                """
                    [
                        {
                            "wordJapanese": "追加",
                            "wordFurigana": "ついか",
                            "wordEnglish": "add",
                            "exampleJapanese": "例文です。",
                            "exampleFurigana": "れいぶんです。",
                            "exampleEnglish": "This is an example sentence."
                        },
                        {
                            "wordJapanese": "開始",
                            "wordFurigana": "かいし",
                            "wordEnglish": "begin",
                            "exampleJapanese": "例文です。",
                            "exampleFurigana": "れいぶんです。",
                            "exampleEnglish": "This is an example sentence."
                        },
                        {
                            "wordJapanese": "終了",
                            "wordFurigana": "しゅうりょう",
                            "wordEnglish": "end",
                            "exampleJapanese": "例文です。",
                            "exampleFurigana": "れいぶんです。",
                            "exampleEnglish": "This is an example sentence."
                        }
                    ]
                """
            )
        );
    }

    @ParameterizedTest
    @MethodSource("searchDictionaryTestProvider")
    public void searchDictionary(String query, String expectedBody) throws Exception {
        IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        var givenUrl = this.getClass().getResource("/dictionary/search/given/");
        databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
        databaseTester.onSetup();

        mockMvc.perform(
            MockMvcRequestBuilders.get("/dictionary/search?query=" + query)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())        
        .andExpect((result) -> JSONAssert.assertEquals(
            expectedBody,
            result.getResponse().getContentAsString(),
            false));
    }

    private static Stream<Arguments> searchDictionaryTestProvider() {
        return Stream.of(
            Arguments.arguments(
                "追加",
                """
                    [
                        {
                            "wordJapanese": "追加",
                            "wordFurigana": "ついか",
                            "wordEnglish": "add",
                            "exampleJapanese": "例文です。",
                            "exampleFurigana": "れいぶんです。",
                            "exampleEnglish": "This is an example sentence."
                        }
                    ]
                """
            ),
            Arguments.arguments(
                "ついか",
                """
                    [
                        {
                            "wordJapanese": "追加",
                            "wordFurigana": "ついか",
                            "wordEnglish": "add",
                            "exampleJapanese": "例文です。",
                            "exampleFurigana": "れいぶんです。",
                            "exampleEnglish": "This is an example sentence."
                        }
                    ]
                """
            ),
            Arguments.arguments(
                "add",
                """
                    [
                        {
                            "wordJapanese": "追加",
                            "wordFurigana": "ついか",
                            "wordEnglish": "add",
                            "exampleJapanese": "例文です。",
                            "exampleFurigana": "れいぶんです。",
                            "exampleEnglish": "This is an example sentence."
                        }
                    ]
                """
            ),
            Arguments.arguments(
                "word",
                """
                    []
                """
            )
        );
    }

}
