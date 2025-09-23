package org.lafeuille.demo.domain

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.server.core.Relation
import java.time.LocalDate

// @see https://schema.org/Person
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "person", collectionRelation = "people")
data class PersonResponse(
    val identifier: String,
    val name: String,
    val givenName: String,
    val familyName: String,
    val birthDate: LocalDate,
    val nationality: CountryResponse,
    val jobTitle: String? = null,
    val address: PostalAddressResponse? = null,
)
