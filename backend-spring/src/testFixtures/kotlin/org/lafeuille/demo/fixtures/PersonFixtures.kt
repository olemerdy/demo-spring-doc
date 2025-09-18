package org.lafeuille.demo.fixtures

import java.time.LocalDate
import java.time.Month

object PersonFixtures {
    val charlesBrown by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.CHARLES,
            familyName = CommonSurnames.BROWN,
            birthDate = LocalDate.of(1950, Month.OCTOBER, 30),
        )
    }

    val jamesBrown by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.JAMES,
            familyName = CommonSurnames.BROWN,
            birthDate = LocalDate.of(1933, Month.MAY, 3),
            deathDate = LocalDate.of(2006, Month.DECEMBER, 25),
        )
    }

    val johnSmith by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.JOHN,
            familyName = CommonSurnames.SMITH,
            birthDate = LocalDate.of(1580, Month.JANUARY, 6),
            deathDate = LocalDate.of(1631, Month.JUNE, 21),
        )
    }

    val johnWilliams by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.JOHN,
            familyName = CommonSurnames.WILLIAMS,
            birthDate = LocalDate.of(1932, Month.FEBRUARY, 8),
        )
    }

    val josephGarcia by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.JOSEPH,
            familyName = CommonSurnames.GARCIA,
            birthDate = LocalDate.of(1966, Month.MARCH, 17),
        )
    }

    val michaelRodriguez by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.MICHAEL,
            familyName = CommonSurnames.RODRIGUEZ,
            birthDate = LocalDate.of(1978, Month.JULY, 12),
        )
    }

    val thomasJohnson by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.THOMAS,
            familyName = CommonSurnames.JOHNSON,
            birthDate = LocalDate.of(1955, Month.FEBRUARY, 28),
        )
    }

    object UnknownGuy {
        const val UID = "unknown.guy"
    }
}
