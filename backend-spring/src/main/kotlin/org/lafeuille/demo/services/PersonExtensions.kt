package org.lafeuille.demo.services

import org.lafeuille.demo.data.PersonEntry
import org.lafeuille.demo.domain.CountryResponse
import org.lafeuille.demo.domain.PersonResponse
import org.lafeuille.demo.domain.PostalAddressResponse
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

internal fun PersonEntry.toPersonResponse(locale: Locale) =
    PersonResponse(
        identifier = uid!!,
        name = commonName!!,
        familyName = surname!!,
        givenName = givenName!!,
        birthDate = PersonExtensions.generalizedTimeToLocalDate(dateOfBirth!!),
        nationality = toCountryResponse(locale),
        jobTitle = title,
        address = toPostalAddressResponse(),
    )

internal fun PersonEntry.toCountryResponse(locale: Locale) =
    if (countryOfCitizenship == null) {
        null
    } else {
        CountryResponse(
            identifier = countryOfCitizenship!!,
            name = PersonExtensions.countryCodeToDisplayCountry(countryOfCitizenship!!, locale),
        )
    }

internal fun PersonEntry.toPostalAddressResponse(): PostalAddressResponse? =
    if (localityName == null && stateOrProvinceName == null && postOfficeBox == null && postalCode == null && streetAddress == null) {
        null
    } else {
        PostalAddressResponse(
            addressLocality = localityName,
            addressRegion = stateOrProvinceName,
            postOfficeBoxNumber = postOfficeBox,
            postalCode = postalCode,
            streetAddress = streetAddress,
        )
    }
