package org.lafeuille.demo.fixtures

import java.time.LocalDate
import java.time.Month
import java.util.Locale

object PersonFixtures {
    val charlesBrown by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.CHARLES,
            familyName = CommonSurnames.BROWN,
            nationality = Locale.US,
            birthDate = LocalDate.of(1950, Month.OCTOBER, 30),
            title = "Lovable loser",
        )
    }

    val jamesBrown by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.JAMES,
            familyName = CommonSurnames.BROWN,
            birthDate = LocalDate.of(1933, Month.MAY, 3),
            nationality = Locale.US,
            deathDate = LocalDate.of(2006, Month.DECEMBER, 25),
            title = "Singer, songwriter, dancer, musician, record producer, bandleader",
        )
    }

    val johnSmith by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.JOHN,
            familyName = CommonSurnames.SMITH,
            birthDate = LocalDate.of(1580, Month.JANUARY, 6),
            nationality = Locale.UK,
            deathDate = LocalDate.of(1631, Month.JUNE, 21),
            title = "Soldier, explorer, colonial governor, admiral of New England, and author",
        )
    }

    val johnWilliams by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.JOHN,
            familyName = CommonSurnames.WILLIAMS,
            nationality = Locale.US,
            birthDate = LocalDate.of(1932, Month.FEBRUARY, 8),
            title = "Composer, conductor, orchestrator, arranger",
        )
    }

    val josephGarcia by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.JOSEPH,
            familyName = CommonSurnames.GARCIA,
            nationality = Locale.FRANCE,
            birthDate = LocalDate.of(1966, Month.MARCH, 17),
            title = "Actor",
        )
    }

    val michaelRodriguez by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.MICHAEL,
            familyName = CommonSurnames.RODRIGUEZ,
            nationality = Locale.US,
            birthDate = LocalDate.of(1978, Month.JULY, 12),
            title = "Actor",
        )
    }

    val robertMiller by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.ROBERT,
            familyName = CommonSurnames.MILLER,
            nationality = Locale.of(null, "NZ"),
            birthDate = LocalDate.of(1944, Month.OCTOBER, 21),
            title = "Professional wrestler",
        )
    }

    val thomasJohnson by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.THOMAS,
            familyName = CommonSurnames.JOHNSON,
            nationality = Locale.FRANCE,
            birthDate = LocalDate.of(1955, Month.FEBRUARY, 28),
            title = "Journalist, movie director",
        )
    }

    object UnknownGuy {
        const val UID = "unknown.guy"
    }
}
