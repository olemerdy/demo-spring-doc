package org.lafeuille.demo.domain

import java.time.LocalDate
import java.util.UUID

// @see https://schema.org/Person
data class PersonResponse(
    val identifier: UUID,
    val birthDate: LocalDate,
    val familyName: String,
    val givenName: String,
)
