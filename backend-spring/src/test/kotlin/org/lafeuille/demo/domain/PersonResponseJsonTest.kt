package org.lafeuille.demo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.lafeuille.demo.fixtures.PersonFixture
import org.lafeuille.demo.fixtures.PersonFixtures.charlesBrown
import org.lafeuille.demo.fixtures.PersonFixtures.jamesBrown
import org.lafeuille.demo.fixtures.PersonFixtures.johnSmith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import java.io.IOException
import java.util.Locale
import java.util.Locale.ENGLISH
import java.util.stream.Stream

@JsonTest
class PersonResponseJsonTest(
    @param:Autowired private val jacksonTester: JacksonTester<PersonResponse>,
) {
    @ParameterizedTest
    @MethodSource("provideFixturesAndFiles")
    @Throws(IOException::class)
    fun serialize(
        personFixture: PersonFixture,
        jsonFileName: String,
        locale: Locale,
    ) {
        val jsonContent = jacksonTester.write(personFixture.toResponse(locale))
        assertThat(jsonContent).isEqualToJson(jsonFileName)
    }

    @ParameterizedTest
    @MethodSource("provideFixturesAndFiles")
    @Throws(IOException::class)
    fun deserialize(
        personFixture: PersonFixture,
        jsonFileName: String,
        locale: Locale,
    ) {
        val personResponse = jacksonTester.readObject(jsonFileName)
        assertThat(personResponse).isEqualTo(personFixture.toResponse(locale))
    }

    companion object {
        @JvmStatic
        fun provideFixturesAndFiles(): Stream<Arguments> =
            Stream.of(
                Arguments.of(charlesBrown, "PersonResponse.CharlesBrown.json", ENGLISH),
                Arguments.of(jamesBrown, "PersonResponse.JamesBrown.json", ENGLISH),
                Arguments.of(johnSmith, "PersonResponse.JohnSmith.json", ENGLISH),
            )
    }
}
