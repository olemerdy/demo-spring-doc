package org.lafeuille.demo.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.lafeuille.demo.domain.PersonFixtures
import org.lafeuille.demo.services.PersonService
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
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
                Optional.of(PersonFixtures.JohnDoe.RESPONSE),
            )

        assertThat(mockMvc.get().uri("/api/v1/people/{uid}", PersonFixtures.JohnDoe.UID))
            .apply(
                document(
                    "GET_people_uid_OK",
                    pathParameters(PersonDescriptors.Parameter.UID),
                    responseFields(PersonDescriptors.Fields.ALL),
                ),
            ).hasStatusOk()
            .bodyJson()
            .hasPathSatisfying("$.identifier") {
                assertThat(it).isEqualTo(PersonFixtures.JohnDoe.UID)
            }.hasPathSatisfying("$.name") {
                assertThat(it).isEqualTo(PersonFixtures.JohnDoe.FULL_NAME)
            }.hasPathSatisfying("$.familyName") {
                assertThat(it).isEqualTo(PersonFixtures.JohnDoe.FAMILY_NAME)
            }.hasPathSatisfying("$.givenName") {
                assertThat(it).isEqualTo(PersonFixtures.JohnDoe.GIVEN_NAME)
            }.hasPathSatisfying("$.birthDate") {
                assertThat(it).isEqualTo(PersonFixtures.JohnDoe.BIRTH_DATE.toString())
            }
    }

    @Test
    fun test_GET_people_uid_NOT_FOUND() {
        whenever(service.getPerson(PersonFixtures.UnknownGuy.UID))
            .thenReturn(
                Optional.empty(),
            )

        assertThat(mockMvc.get().uri("/api/v1/people/{uid}", PersonFixtures.UnknownGuy.UID))
            .apply(
                document(
                    "GET_people_uid_NOT_FOUND",
                    pathParameters(PersonDescriptors.Parameter.UID),
                ),
            ).hasStatus(NOT_FOUND)
    }

    @Test
    fun test_DELETE_people_uid_NO_CONTENT() {
        assertThat(mockMvc.delete().uri("/api/v1/people/{uid}", PersonFixtures.JohnDoe.UID))
            .apply(
                document(
                    "DELETE_people_uid_NO_CONTENT",
                    pathParameters(PersonDescriptors.Parameter.UID),
                ),
            ).hasStatus(NO_CONTENT)
    }
}
