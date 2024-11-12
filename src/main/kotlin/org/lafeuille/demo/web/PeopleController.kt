package org.lafeuille.demo.web

import org.lafeuille.demo.domain.PersonResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.Month

@RestController
@RequestMapping("api/v1/people")
class PeopleController {
    @GetMapping("{identifier}")
    fun readPerson(
        @PathVariable identifier: String,
    ): PersonResponse {
        return PersonResponse(
            identifier = identifier,
            birthDate = LocalDate.of(2001, Month.JANUARY, 1),
            familyName = "Doe",
            givenName = "John",
        )
    }
}
