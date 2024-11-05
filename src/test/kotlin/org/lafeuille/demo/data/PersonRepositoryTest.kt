package org.lafeuille.demo.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.ldap.DataLdapTest

@DataLdapTest
class PersonRepositoryTest(
    @Autowired val repository: PersonRepository,
) {
    @Test
    fun test() {
        val people = repository.findAll()
        assertThat(people).isEmpty()
    }
}
