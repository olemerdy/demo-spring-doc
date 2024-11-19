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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.Optional

@WebMvcTest(controllers = [PeopleController::class])
class PeopleControllerTest(
    @Autowired val mockMvc: MockMvc,
) {
    @MockBean
    lateinit var service: PersonService

    @Test
    fun test() {
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
    }
}
