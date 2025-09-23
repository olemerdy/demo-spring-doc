package org.lafeuille.demo.services

import org.lafeuille.demo.data.PersonEntry
import org.lafeuille.demo.domain.CountryResponse
import org.lafeuille.demo.domain.PersonResponse
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object PersonExtensions {
    internal val GENERALIZED_TIME_FORMATTER: DateTimeFormatter by lazy { DateTimeFormatter.ofPattern("yyyyMMddHH[mm]VV") }

    fun generalizedTimeToLocalDate(generalizedTime: String): LocalDate =
        ZonedDateTime.parse(generalizedTime, GENERALIZED_TIME_FORMATTER).toLocalDate()

    fun countryCodeToDisplayCountry(
        countryCode: String,
        locale: Locale,
    ): String = Locale.of("", countryCode).getDisplayCountry(locale)
}

internal fun PersonEntry.toResponse(locale: Locale) =
    PersonResponse(
        identifier = this.uid!!,
        name = this.commonName!!,
        familyName = this.surname!!,
        givenName = this.givenName!!,
        birthDate = PersonExtensions.generalizedTimeToLocalDate(this.dateOfBirth!!),
        nationality =
            CountryResponse(
                identifier = this.countryOfCitizenship!!,
                name = PersonExtensions.countryCodeToDisplayCountry(this.countryOfCitizenship!!, locale),
            ),
    )
