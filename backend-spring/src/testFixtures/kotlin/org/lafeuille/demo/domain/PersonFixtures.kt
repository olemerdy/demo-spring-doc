package org.lafeuille.demo.domain

import java.time.LocalDate
import java.time.Month

object PersonFixtures {
    object JohnDoe {
        const val UID = "john.doe"

        const val GIVEN_NAME = "John"

        const val FAMILY_NAME = "Doe"

        const val FULL_NAME = "$GIVEN_NAME $FAMILY_NAME"

        val BIRTH_DATE: LocalDate by lazy { LocalDate.of(2001, Month.JANUARY, 1) }
    }

    object UnknownGuy {
        const val UID = "unknown.guy"
    }

    const val OTHER_GIVEN_NAME = "Jane"

    const val OTHER_FAMILY_NAME = "Smith"

    const val OTHER_FULL_NAME = "$OTHER_GIVEN_NAME $OTHER_FAMILY_NAME"

    val OTHER_BIRTH_DATE: LocalDate by lazy { LocalDate.of(2002, Month.FEBRUARY, 2) }
}
