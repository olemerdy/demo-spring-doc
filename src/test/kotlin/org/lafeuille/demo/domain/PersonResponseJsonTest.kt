package org.lafeuille.demo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import java.io.IOException

@JsonTest
class PersonResponseJsonTest(
    @Autowired private val jacksonTester: JacksonTester<PersonResponse>,
) {
    @Test
    @Throws(IOException::class)
    fun serialize() {
        val personResponse =
            PersonResponse(
                uid = PersonFixtures.UID,
                givenName = PersonFixtures.GIVEN_NAME,
                familyName = PersonFixtures.FAMILY_NAME,
                birthDate = PersonFixtures.BIRTH_DATE,
            )
        val jsonContent = jacksonTester.write(personResponse)
        assertThat(jsonContent).isEqualToJson("PersonResponse.json")
    }

    @Test
    @Throws(IOException::class)
    fun deserialize() {
        val (uid, birthDate, familyName, givenName) = jacksonTester.readObject("PersonResponse.json")
        assertThat(birthDate).isEqualTo(PersonFixtures.BIRTH_DATE)
        assertThat(givenName).isEqualTo(PersonFixtures.GIVEN_NAME)
        assertThat(familyName).isEqualTo(PersonFixtures.FAMILY_NAME)
    }
}
