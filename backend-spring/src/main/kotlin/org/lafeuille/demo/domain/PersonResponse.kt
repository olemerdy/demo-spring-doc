package org.lafeuille.demo.domain

import org.springframework.hateoas.server.core.Relation
import java.time.LocalDate

// @see https://schema.org/Person
@Relation(itemRelation = "person", collectionRelation = "people")
data class PersonResponse(
    val identifier: String,
    val name: String,
    val birthDate: LocalDate,
    val familyName: String,
    val givenName: String,
)
