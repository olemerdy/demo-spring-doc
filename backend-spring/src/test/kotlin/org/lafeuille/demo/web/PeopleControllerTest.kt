package org.lafeuille.demo.web

import org.junit.jupiter.api.Test
import org.lafeuille.demo.domain.PersonFixtures
import org.lafeuille.demo.domain.PersonResponse
import org.lafeuille.demo.services.PersonService
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.Optional

@WebMvcTest(controllers = [PeopleController::class])
class PeopleControllerTest(
    @param:Autowired val mockMvc: MockMvc,
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

        mockMvc
            .perform(get("/api/v1/people/{uid}", PersonFixtures.JohnDoe.UID))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.identifier").value(PersonFixtures.JohnDoe.UID))
            .andExpect(jsonPath("$.name").value(PersonFixtures.JohnDoe.FULL_NAME))
            .andExpect(jsonPath("$.familyName").value(PersonFixtures.JohnDoe.FAMILY_NAME))
            .andExpect(jsonPath("$.givenName").value(PersonFixtures.JohnDoe.GIVEN_NAME))
            .andExpect(jsonPath("$.birthDate").value(PersonFixtures.JohnDoe.BIRTH_DATE.toString()))
    }

    @Test
    fun test_GET_people_uid_NOT_FOUND() {
        whenever(service.getPerson(PersonFixtures.UnknownGuy.UID))
            .thenReturn(
                Optional.empty(),
            )

        mockMvc
            .perform(get("/api/v1/people/{uid}", PersonFixtures.UnknownGuy.UID))
            .andExpect(status().isNotFound)
    }

    @Test
    fun test_DELETE_people_uid_NO_CONTENT() {
        mockMvc
            .perform(delete("/api/v1/people/{uid}", PersonFixtures.JohnDoe.UID))
            .andExpect(status().isNoContent)
    }
}
