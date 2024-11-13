package org.lafeuille.demo.web

import org.junit.jupiter.api.Test
import org.lafeuille.demo.domain.PersonFixtures
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [PeopleController::class])
class PeopleControllerTest(
    @Autowired val mockMvc: MockMvc,
) {
    @Test
    fun test() {
        mockMvc.perform(get("/api/v1/people/{uid}", PersonFixtures.UID))
            .andExpect(status().isOk)
    }
}
