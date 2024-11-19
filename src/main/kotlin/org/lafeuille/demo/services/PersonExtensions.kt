package org.lafeuille.demo.services

import org.lafeuille.demo.data.Person
import org.lafeuille.demo.domain.PersonResponse
import java.time.LocalDate
import java.time.Month

internal fun Person.toResponse() =
    PersonResponse(
        identifier = this.uid!!,
        name = this.commonName!!,
        familyName = this.surname!!,
        givenName = this.givenName!!,
        birthDate = LocalDate.of(2001, Month.JANUARY, 1),
    )
