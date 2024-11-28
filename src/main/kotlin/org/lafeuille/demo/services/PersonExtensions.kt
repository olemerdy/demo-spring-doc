package org.lafeuille.demo.services

import org.lafeuille.demo.data.Person
import org.lafeuille.demo.domain.PersonResponse
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object PersonExtensions {
    internal val GENERALIZED_TIME_FORMATTER: DateTimeFormatter by lazy { DateTimeFormatter.ofPattern("yyyyMMddHH[mm]VV") }
}

internal fun Person.toResponse() =
    PersonResponse(
        identifier = this.uid!!,
        name = this.commonName!!,
        familyName = this.surname!!,
        givenName = this.givenName!!,
        birthDate = ZonedDateTime.parse(this.dateOfBirth!!, PersonExtensions.GENERALIZED_TIME_FORMATTER).toLocalDate(),
    )
