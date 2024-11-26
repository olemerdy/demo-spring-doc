package org.lafeuille.demo.web

import org.junit.jupiter.api.Test
import org.lafeuille.demo.domain.PersonFixtures
import org.lafeuille.demo.domain.PersonResponse
import org.lafeuille.demo.services.PersonService
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.Optional

@WebMvcTest(controllers = [PeopleController::class])
class PeopleControllerTest(
    @Autowired val mockMvc: MockMvc,
) {
    @MockBean
    lateinit var service: PersonService

    @Test
    fun test_GET_people_uid_OK() {
        whenever(service.getPerson(PersonFixtures.UID))
            .thenReturn(
                Optional.of(
                    PersonResponse(
                        identifier = PersonFixtures.UID,
                        name = PersonFixtures.FULL_NAME,
                        birthDate = PersonFixtures.BIRTH_DATE,
                        givenName = PersonFixtures.GIVEN_NAME,
                        familyName = PersonFixtures.FAMILY_NAME,
                    ),
                ),
            )

        mockMvc.perform(get("/api/v1/people/{uid}", PersonFixtures.UID))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$.identifier").value(PersonFixtures.UID))
            .andExpect(jsonPath("\$.name").value(PersonFixtures.FULL_NAME))
            .andExpect(jsonPath("\$.familyName").value(PersonFixtures.FAMILY_NAME))
            .andExpect(jsonPath("\$.givenName").value(PersonFixtures.GIVEN_NAME))
            .andExpect(jsonPath("\$.birthDate").value(PersonFixtures.BIRTH_DATE.toString()))
    }

    @Test
    fun test_GET_people_uid_NOT_FOUND() {
        whenever(service.getPerson(PersonFixtures.UID))
            .thenReturn(
                Optional.empty(),
            )

        mockMvc.perform(get("/api/v1/people/{uid}", PersonFixtures.UID))
            .andExpect(status().isNotFound)
    }

    @Test
    fun test_DELETE_people_uid_NO_CONTENT() {
        mockMvc.perform(delete("/api/v1/people/{uid}", PersonFixtures.UID))
            .andExpect(status().isNoContent)
    }
}
