package org.lafeuille.demo.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.lafeuille.demo.fixtures.PersonFixtures
import org.lafeuille.demo.fixtures.PersonFixtures.charlesBrown
import org.lafeuille.demo.fixtures.PersonFixtures.johnSmith
import org.lafeuille.demo.services.PersonService
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.restdocs.request.RequestDocumentation.queryParameters
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.assertj.MockMvcTester
import java.util.Optional

@WebMvcTest(controllers = [PeopleController::class])
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "lafeuille.org", uriPort = 443)
class PeopleControllerTest(
    @param:Autowired val mockMvc: MockMvcTester,
) {
    @MockitoBean
    lateinit var service: PersonService

    @Test
    fun test_GET_people_empty_OK() {
        val pageable = Pageable.ofSize(20)

        whenever(service.getPeople(pageable))
            .thenReturn(
                Page.empty(pageable),
            )

        assertThat(mockMvc.get().uri("/api/v1/people"))
            .apply(
                document("GET_people_empty_OK"),
            ).hasStatusOk()
            .bodyJson()
    }

    @Test
    fun test_GET_people_not_empty_OK() {
        val pageable =
            PageRequest.of(
                0,
                5,
                Sort.by(
                    Sort.Order.by("familyName"),
                    Sort.Order.asc("givenName"),
                ),
            )

        whenever(service.getPeople(pageable))
            .thenReturn(
                PageImpl(
                    listOf(
                        johnSmith.toResponse(),
                        charlesBrown.toResponse(),
                    ),
                    pageable,
                    2,
                ),
            )

        assertThat(
            mockMvc.get().uri(
                "/api/v1/people?page={page}&size={size}&sort={prop1}&sort={prop2}",
                0,
                5,
                "familyName",
                "givenName,asc",
            ),
        ).apply(
            document(
                "GET_people_not_empty_OK",
                queryParameters(PageDescriptors.Parameters.ALL),
            ),
        ).hasStatusOk()
            .bodyJson()
    }

    @Test
    fun test_GET_people_uid_OK() {
        whenever(service.getPerson(johnSmith.uid))
            .thenReturn(
                Optional.of(johnSmith.toResponse()),
            )

        assertThat(mockMvc.get().uri("/api/v1/people/{uid}", johnSmith.uid))
            .apply(
                document(
                    "GET_people_uid_OK",
                    pathParameters(PersonDescriptors.Parameter.UID),
                    responseFields(PersonDescriptors.Fields.ALL),
                ),
            ).hasStatusOk()
            .bodyJson()
            .hasPathSatisfying("$.identifier") {
                assertThat(it).isEqualTo(johnSmith.uid)
            }.hasPathSatisfying("$.name") {
                assertThat(it).isEqualTo(johnSmith.fullName)
            }.hasPathSatisfying("$.familyName") {
                assertThat(it).isEqualTo(johnSmith.familyName)
            }.hasPathSatisfying("$.givenName") {
                assertThat(it).isEqualTo(johnSmith.givenName)
            }.hasPathSatisfying("$.birthDate") {
                assertThat(it).isEqualTo(johnSmith.birthDate.toString())
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
        assertThat(mockMvc.delete().uri("/api/v1/people/{uid}", johnSmith.uid))
            .apply(
                document(
                    "DELETE_people_uid_NO_CONTENT",
                    pathParameters(PersonDescriptors.Parameter.UID),
                ),
            ).hasStatus(NO_CONTENT)
    }
}
