package org.lafeuille.demo.data

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Index
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.ldap.DataLdapTest
import java.util.function.Consumer

@DataLdapTest
class PersonRepositoryTest(
    @Autowired val repository: PersonRepository,
) {
    @Test
    fun find_all() {
        val people = repository.findAll()
        assertThat(people)
            .hasSize(2)
            .allSatisfy(
                Consumer {
                    assertThat(it.dn?.get(0)).isEqualTo("dc=org")
                    assertThat(it.dn?.get(1)).isEqualTo("dc=lafeuille")
                    assertThat(it.dn?.get(2)).isEqualTo("ou=people")
                },
            )
            .satisfies(
                {
                    assertThat(it.dn?.get(3)).isEqualTo("uid=jane.smith")
                    assertThat(it.uid).isEqualTo("jane.smith")
                    assertThat(it.commonName).isEqualTo("Jane Smith")
                    assertThat(it.surname).isEqualTo("Smith")
                    assertThat(it.givenName).isEqualTo("Jane")
                },
                Index.atIndex(0),
            )
            .satisfies(
                {
                    assertThat(it.dn?.get(3)).isEqualTo("uid=john.doe")
                    assertThat(it.uid).isEqualTo("john.doe")
                    assertThat(it.commonName).isEqualTo("John Doe")
                    assertThat(it.surname).isEqualTo("Doe")
                    assertThat(it.givenName).isEqualTo("John")
                },
                Index.atIndex(1),
            )
    }

    @Test
    fun find_by_id() {
        val name = PersonEntry.uidToName("john.doe")
        val personOpt = repository.findById(name)

        assertThat(personOpt).hasValueSatisfying {
            assertThat(it.dn?.get(0)).isEqualTo("dc=org")
            assertThat(it.dn?.get(1)).isEqualTo("dc=lafeuille")
            assertThat(it.dn?.get(2)).isEqualTo("ou=people")
            assertThat(it.dn?.get(3)).isEqualTo("uid=john.doe")

            assertThat(it.uid).isEqualTo("john.doe")
            assertThat(it.commonName).isEqualTo("John Doe")
            assertThat(it.surname).isEqualTo("Doe")
            assertThat(it.givenName).isEqualTo("John")
        }
    }
}
