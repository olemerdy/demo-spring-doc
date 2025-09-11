package org.lafeuille.demo.domain

import java.time.LocalDate
import java.time.Month

object PersonFixtures {
    object JaneSmith {
        const val UID = "jane.smith"

        const val GIVEN_NAME = "Jane"

        const val FAMILY_NAME = "Smith"

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

    object JohnDoe {
        const val UID = "john.doe"

        const val GIVEN_NAME = "John"

        const val FAMILY_NAME = "Doe"

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

    object UnknownGuy {
        const val UID = "unknown.guy"
    }
}
