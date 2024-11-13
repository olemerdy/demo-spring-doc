package org.lafeuille.demo.web

import org.lafeuille.demo.domain.PersonResponse
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.Month

@RestController
@RequestMapping("api/v1/people")
class PeopleController {
    @GetMapping
    fun readPeople(): Page<PersonResponse> = Page.empty()

    @GetMapping("{uid}")
    fun readPerson(
        @PathVariable uid: String,
    ) = PersonResponse(
        identifier = uid,
        name = "John Doe",
        birthDate = LocalDate.of(2001, Month.JANUARY, 1),
        familyName = "Doe",
        givenName = "John",
    )

    @DeleteMapping("{uid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePerson(
        @PathVariable uid: String,
    ) {
    }
}
