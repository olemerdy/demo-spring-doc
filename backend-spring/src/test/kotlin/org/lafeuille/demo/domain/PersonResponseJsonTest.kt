package org.lafeuille.demo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import java.io.IOException

@JsonTest
class PersonResponseJsonTest(
    @param:Autowired private val jacksonTester: JacksonTester<PersonResponse>,
) {
    @Test
    @Throws(IOException::class)
    fun serialize() {
        val personResponse =
            PersonResponse(
                identifier = PersonFixtures.JohnDoe.UID,
                name = PersonFixtures.JohnDoe.FULL_NAME,
                givenName = PersonFixtures.JohnDoe.GIVEN_NAME,
                familyName = PersonFixtures.JohnDoe.FAMILY_NAME,
                birthDate = PersonFixtures.JohnDoe.BIRTH_DATE,
            )
        val jsonContent = jacksonTester.write(personResponse)
        assertThat(jsonContent).isEqualToJson("PersonResponse.JohnDoe.json")
    }

    @Test
    @Throws(IOException::class)
    fun deserialize() {
        val (identifier, name, birthDate, familyName, givenName) = jacksonTester.readObject("PersonResponse.JohnDoe.json")
        assertThat(identifier).isEqualTo(PersonFixtures.JohnDoe.UID)
        assertThat(name).isEqualTo(PersonFixtures.JohnDoe.FULL_NAME)
        assertThat(birthDate).isEqualTo(PersonFixtures.JohnDoe.BIRTH_DATE)
        assertThat(givenName).isEqualTo(PersonFixtures.JohnDoe.GIVEN_NAME)
        assertThat(familyName).isEqualTo(PersonFixtures.JohnDoe.FAMILY_NAME)
    }
}
