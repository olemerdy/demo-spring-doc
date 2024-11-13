package org.lafeuille.demo.services

import org.lafeuille.demo.data.Person
import org.lafeuille.demo.domain.PersonResponse
import java.time.LocalDate

internal fun Person.toResponse() =
    PersonResponse(
        identifier = this.dn?.get(0)!!,
        name = this.fullName!!,
        birthDate = LocalDate.parse(this.birthDate!!),
        familyName = this.lastName!!,
        givenName = this.firstName!!,
    )
