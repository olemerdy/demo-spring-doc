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
    ) {
        val jsonContent = jacksonTester.write(personFixture.toResponse())
        assertThat(jsonContent).isEqualToJson(jsonFileName)
    }

    @ParameterizedTest
    @MethodSource("provideFixturesAndFiles")
    @Throws(IOException::class)
    fun deserialize(
        personFixture: PersonFixture,
        jsonFileName: String,
    ) {
        val personResponse = jacksonTester.readObject(jsonFileName)
        assertThat(personResponse).isEqualTo(personFixture.toResponse())
    }

    companion object {
        @JvmStatic
        fun provideFixturesAndFiles(): Stream<Arguments> =
            Stream.of(
                Arguments.of(charlesBrown, "PersonResponse.CharlesBrown.json"),
                Arguments.of(jamesBrown, "PersonResponse.JamesBrown.json"),
                Arguments.of(johnSmith, "PersonResponse.JohnSmith.json"),
            )
    }
}
