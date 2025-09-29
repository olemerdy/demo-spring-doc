package org.lafeuille.demo.fixtures

import java.time.LocalDate
import java.time.Month
import java.util.Locale

object PersonFixtures {
    val barbaraJones by lazy {
        PersonFixture(
            givenName = CommonFemaleGivenNames.BARBARA,
            familyName = CommonSurnames.JONES,
            nationality = Locale.of("", "JM"),
            birthDate = LocalDate.of(1952, Month.JULY, 1),
            deathDate = LocalDate.of(2014, Month.DECEMBER, 19),
            title = "Singer",
        )
    }

    val charlesBrown by lazy {
        PersonFixture(
            givenName = CommonMaleGivenNames.CHARLES,
            familyName = CommonSurnames.BROWN,
            nationality = Locale.US,
            birthDate = LocalDate.of(1950, Month.OCTOBER, 30),
            title = "Lovable loser",
        )
    }

    val elizabethGarcia by lazy {
        PersonFixture(
            givenName = CommonFemaleGivenNames.ELIZABETH,
            familyName = CommonSurnames.GARCIA,
            nationality = Locale.of("", "CZ"),
            birthDate = LocalDate.of(2001, Month.JANUARY, 1),
            address = AddressFixtures.overTheRainbow,
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
            title = "Soldier, explorer, admiral of New England, and author",
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

    val lindaMartinez by lazy {
        PersonFixture(
            givenName = CommonFemaleGivenNames.LINDA,
            familyName = CommonSurnames.MARTINEZ,
            nationality = Locale.US,
            birthDate = LocalDate.of(1975, Month.DECEMBER, 2),
            deathDate = LocalDate.of(2005, Month.MAY, 19),
            title = "Pianist, composer",
        )
    }

    val maryDavis by lazy {
        PersonFixture(
            givenName = CommonFemaleGivenNames.MARY,
            familyName = CommonSurnames.DAVIS,
            nationality = Locale.of("", "IE"),
            birthDate = LocalDate.of(1954, Month.AUGUST, 6),
            title = "Social entrepreneur, activist",
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

    val patriciaDavis by lazy {
        PersonFixture(
            givenName = CommonFemaleGivenNames.PATRICIA,
            familyName = CommonSurnames.DAVIS,
            nationality = Locale.US,
            birthDate = LocalDate.of(1956, Month.MARCH, 1),
            title = "Writer, screenwriter",
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
