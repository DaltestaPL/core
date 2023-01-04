package com.example.core.book;

import com.example.core.book_DDD.domain.rest.BookSearchCriteriaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(scripts = "/sql/book-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "/sql/book-cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
})
public class BookTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ArgumentsSource(CriteriaProvider.class)
    public void whenGetBooksByCriteriaParemetrized_thenOkResponse(Integer releaseYear,
                                                                           String title,
                                                                           Integer result) throws Exception {
        // given
        var criteria = BookSearchCriteriaRequest.builder()
                .releaseYear(releaseYear)
                .title(title)
                .build();
        var criteriaJson = new ObjectMapper().writeValueAsString(criteria);

        // when && then
        mockMvc.perform(post("/search?page=0&size=10&sort=id,DESC")
                        .content(criteriaJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", Matchers.equalTo(result)));
    }

    static class CriteriaProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(1995, "Nowa ksiazka", 1),
                    Arguments.of(1995, "Stara ksiazka", 0)
            );
        }
    }
}
