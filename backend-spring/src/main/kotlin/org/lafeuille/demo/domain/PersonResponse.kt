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
    @field:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val nationality: CountryResponse? = null,
    val jobTitle: String? = null,
    @field:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val address: PostalAddressResponse? = null,
)
