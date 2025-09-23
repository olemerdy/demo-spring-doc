package org.lafeuille.demo.fixtures

import org.lafeuille.demo.domain.CountryResponse
import org.lafeuille.demo.domain.PersonResponse
import java.time.LocalDate
import java.util.Locale

data class PersonFixture(
    val givenName: String,
    val familyName: String,
    val nationality: Locale,
    val birthDate: LocalDate,
    val deathDate: LocalDate? = null,
) {
    val uid: String
        get() = "${givenName.lowercase()}.${familyName.lowercase()}"
    val fullName: String
        get() = "$givenName $familyName"

    fun toResponse(locale: Locale) =
        PersonResponse(
            identifier = uid,
            name = fullName,
            givenName = givenName,
            familyName = familyName,
            birthDate = birthDate,
            nationality =
                CountryResponse(
                    identifier = nationality.country,
                    name = nationality.getDisplayCountry(locale),
                ),
        )
}

// United States (most common names, 1990 census)
// https://en.wikipedia.org/wiki/List_of_most_popular_given_names#Male_names_2
object CommonMaleGivenNames {
    const val JAMES = "James"
    const val JOHN = "John"
    const val ROBERT = "Robert"
    const val MICHAEL = "Michael"
    const val WILLIAM = "William"
    const val DAVID = "David"
    const val RICHARD = "Richard"
    const val CHARLES = "Charles"
    const val JOSEPH = "Joseph"
    const val THOMAS = "Thomas"
}

// United States (most common names, 1990 census)
// https://en.wikipedia.org/wiki/List_of_most_popular_given_names#Female_names_2
object CommonFemaleGivenNames {
    const val MARY = "Mary"
    const val PATRICIA = "Patricia"
    const val LINDA = "Linda"
    const val BARBARA = "Barbara"
    const val ELIZABETH = "Elizabeth"
    const val JENNIFER = "Jennifer"
    const val MARIA = "Maria"
    const val SUSAN = "Susan"
    const val MARGARET = "Margaret"
    const val DOROTHY = "Dorothy"
}

// https://en.wikipedia.org/wiki/Lists_of_most_common_surnames_in_North_American_countries#United_States
object CommonSurnames {
    const val SMITH = "Smith"
    const val JOHNSON = "Johnson"
    const val WILLIAMS = "Williams"
    const val BROWN = "Brown"
    const val JONES = "Jones"
    const val GARCIA = "Garcia"
    const val MILLER = "Miller"
    const val DAVIS = "Davis"
    const val RODRIGUEZ = "Rodriguez"
    const val MARTINEZ = "Martinez"
}
