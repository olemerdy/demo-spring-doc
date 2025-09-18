package org.lafeuille.demo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.lafeuille.demo.fixtures.person.JohnSmith
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
        val jsonContent = jacksonTester.write(JohnSmith.RESPONSE)
        assertThat(jsonContent).isEqualToJson("PersonResponse.JohnSmith.json")
    }

    @Test
    @Throws(IOException::class)
    fun deserialize() {
        val personResponse = jacksonTester.readObject("PersonResponse.JohnSmith.json")
        assertThat(personResponse).isEqualTo(JohnSmith.RESPONSE)
    }
}
