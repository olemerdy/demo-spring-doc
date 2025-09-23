package org.lafeuille.demo.services

import org.lafeuille.demo.data.PersonEntry
import org.lafeuille.demo.domain.CountryResponse
import org.lafeuille.demo.domain.PersonResponse
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object PersonExtensions {
    internal val GENERALIZED_TIME_FORMATTER: DateTimeFormatter by lazy { DateTimeFormatter.ofPattern("yyyyMMddHH[mm]VV") }
}

internal fun PersonEntry.toResponse(locale: Locale) =
    PersonResponse(
        identifier = this.uid!!,
        name = this.commonName!!,
        familyName = this.surname!!,
        givenName = this.givenName!!,
        birthDate = ZonedDateTime.parse(this.dateOfBirth!!, PersonExtensions.GENERALIZED_TIME_FORMATTER).toLocalDate(),
        nationality =
            CountryResponse(
                identifier = this.countryOfCitizenship!!,
                name = Locale.of("", this.countryOfCitizenship!!).getDisplayCountry(locale),
            ),
    )
