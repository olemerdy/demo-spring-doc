package org.lafeuille.demo.domain

import java.time.LocalDate

// @see https://schema.org/Person
data class PersonResponse(
    val uid: String,
    val birthDate: LocalDate,
    val familyName: String,
    val givenName: String,
)
