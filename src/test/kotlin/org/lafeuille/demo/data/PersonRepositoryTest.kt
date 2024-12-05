package org.lafeuille.demo.data

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Index
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.ldap.DataLdapTest
import java.util.function.Consumer

@DataLdapTest
@TestMethodOrder(
    MethodOrderer.OrderAnnotation::class,
)
class PersonRepositoryTest(
    @Autowired val repository: PersonRepository,
) {
    @Test
    @Order(1)
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
    @Order(2)
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

    @Test
    @Order(3)
    fun save_and_check_id() {
        val person =
            PersonEntry(
                dn = null,
                uid = "john.smith",
                commonName = "John Smith",
                dateOfBirth = "2003030300Z",
                givenName = "John",
                surname = "Smith",
            )
        assertThat(person.dn).isNull()
        assertThat(person.isNew).isTrue()

        val saved = repository.save(person)
        assertThat(saved).satisfies(
            Consumer {
                assertThat(it.isNew).isFalse()

                assertThat(it.dn?.get(0)).isEqualTo("dc=org")
                assertThat(it.dn?.get(1)).isEqualTo("dc=lafeuille")
                assertThat(it.dn?.get(2)).isEqualTo("ou=people")
                assertThat(it.dn?.get(3)).isEqualTo("uid=john.smith")

                assertThat(it.uid).isEqualTo("john.smith")
                assertThat(it.commonName).isEqualTo("John Smith")
                assertThat(it.dateOfBirth).isEqualTo("2003030300Z")
                assertThat(it.surname).isEqualTo("Smith")
                assertThat(it.givenName).isEqualTo("John")
            },
        )
    }

    @Test
    @Order(4) // Ordering because we modify an entry that is checked by another test. Change for distinct dataset
    fun update() {
        val name = PersonEntry.uidToName("john.doe")
        val personOpt = repository.findById(name)

        assertThat(personOpt).isNotEmpty

        val person = personOpt.get()
        person.surname = "Wesson"
        person.commonName = "John Wesson"

        val saved = repository.save(person)
        assertThat(saved).satisfies(
            Consumer {
                assertThat(it.isNew).isFalse()

                assertThat(it.dn?.get(0)).isEqualTo("dc=org")
                assertThat(it.dn?.get(1)).isEqualTo("dc=lafeuille")
                assertThat(it.dn?.get(2)).isEqualTo("ou=people")
                assertThat(it.dn?.get(3)).isEqualTo("uid=john.doe")

                assertThat(it.uid).isEqualTo("john.doe")
                assertThat(it.commonName).isEqualTo("John Wesson")
                assertThat(it.dateOfBirth).isEqualTo("2001010100Z")
                assertThat(it.surname).isEqualTo("Wesson")
                assertThat(it.givenName).isEqualTo("John")
            },
        )
    }
}
