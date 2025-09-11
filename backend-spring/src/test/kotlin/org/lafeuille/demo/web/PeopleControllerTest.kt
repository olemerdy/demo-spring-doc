package org.lafeuille.demo.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.lafeuille.demo.domain.PersonFixtures
import org.lafeuille.demo.domain.PersonResponse
import org.lafeuille.demo.services.PersonService
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.assertj.MockMvcTester
import java.util.Optional

@WebMvcTest(controllers = [PeopleController::class])
@AutoConfigureRestDocs
class PeopleControllerTest(
    @param:Autowired val mockMvc: MockMvcTester,
) {
    @MockitoBean
    lateinit var service: PersonService

    @Test
    fun test_GET_people_uid_OK() {
        whenever(service.getPerson(PersonFixtures.JohnDoe.UID))
            .thenReturn(
                Optional.of(
                    PersonResponse(
                        identifier = PersonFixtures.JohnDoe.UID,
                        name = PersonFixtures.JohnDoe.FULL_NAME,
                        birthDate = PersonFixtures.JohnDoe.BIRTH_DATE,
                        givenName = PersonFixtures.JohnDoe.GIVEN_NAME,
                        familyName = PersonFixtures.JohnDoe.FAMILY_NAME,
                    ),
                ),
            )

        assertThat(mockMvc.get().uri("/api/v1/people/{uid}", PersonFixtures.JohnDoe.UID))
            .hasStatusOk()
            .bodyJson()
            .hasPath("$.identifier").isEqualTo(PersonFixtures.JohnDoe.UID)
            .hasPath("$.name").isEqualTo(PersonFixtures.JohnDoe.FULL_NAME)
            .hasPath("$.familyName").isEqualTo(PersonFixtures.JohnDoe.FAMILY_NAME)
            .hasPath("$.givenName").isEqualTo(PersonFixtures.JohnDoe.GIVEN_NAME)
            .hasPath("$.birthDate").isEqualTo(PersonFixtures.JohnDoe.BIRTH_DATE.toString())
            .apply { document("GET_people_uid_OK") }
    }

    @Test
    fun test_GET_people_uid_NOT_FOUND() {
        whenever(service.getPerson(PersonFixtures.UnknownGuy.UID))
            .thenReturn(
                Optional.empty(),
            )

        assertThat(mockMvc.get().uri("/api/v1/people/{uid}", PersonFixtures.UnknownGuy.UID))
            .hasStatus(NOT_FOUND)
            .apply { document("test_GET_people_uid_NOT_FOUND") }
    }

    @Test
    fun test_DELETE_people_uid_NO_CONTENT() {
        assertThat(mockMvc.delete().uri("/api/v1/people/{uid}", PersonFixtures.JohnDoe.UID))
            .hasStatus(NO_CONTENT)
            .apply { document("test_DELETE_people_uid_NO_CONTENT") }
    }
}
