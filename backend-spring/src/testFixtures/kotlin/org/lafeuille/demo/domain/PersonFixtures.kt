package org.lafeuille.demo.domain

import java.time.LocalDate
import java.time.Month

object PersonFixtures {
    // United States (most common names, 1990 census)
    // https://en.wikipedia.org/wiki/List_of_most_popular_given_names#Male_names_2
    object MostCommonMaleGivenNames {
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
    object MostCommonFemaleGivenNames {
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
    object MostCommonSurnames {
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

    object JohnSmith {
        const val UID = "john.smith"
        const val GIVEN_NAME = MostCommonMaleGivenNames.JOHN
        const val FAMILY_NAME = MostCommonSurnames.SMITH
        const val FULL_NAME = "$GIVEN_NAME $FAMILY_NAME"
        val BIRTH_DATE: LocalDate by lazy { LocalDate.of(2001, Month.JANUARY, 1) }

        val RESPONSE: PersonResponse by lazy {
            PersonResponse(
                identifier = UID,
                name = FULL_NAME,
                givenName = GIVEN_NAME,
                familyName = FAMILY_NAME,
                birthDate = BIRTH_DATE,
            )
        }
    }

    object JaneSmith {
        const val UID = "jane.smith"

        const val GIVEN_NAME = "Jane"

        const val FAMILY_NAME = MostCommonSurnames.SMITH

        const val FULL_NAME = "$GIVEN_NAME $FAMILY_NAME"

        val BIRTH_DATE: LocalDate by lazy { LocalDate.of(2002, Month.FEBRUARY, 2) }

        val RESPONSE: PersonResponse by lazy {
            PersonResponse(
                identifier = UID,
                name = FULL_NAME,
                givenName = GIVEN_NAME,
                familyName = FAMILY_NAME,
                birthDate = BIRTH_DATE,
            )
        }
    }

    object UnknownGuy {
        const val UID = "unknown.guy"
    }
}
