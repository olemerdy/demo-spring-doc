package org.lafeuille.demo.domain

import java.time.LocalDate

// @see https://schema.org/Person
data class PersonResponse(
    val identifier: String,
    val name: String,
    val birthDate: LocalDate,
    val familyName: String,
    val givenName: String,
)
