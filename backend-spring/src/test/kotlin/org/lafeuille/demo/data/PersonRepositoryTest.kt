package org.lafeuille.demo.data

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Index
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.lafeuille.demo.fixtures.CommonSurnames
import org.lafeuille.demo.fixtures.PersonFixtures.johnSmith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.ldap.DataLdapTest
import java.util.function.Consumer

@DataLdapTest
@TestMethodOrder(
    MethodOrderer.OrderAnnotation::class,
)
class PersonRepositoryTest(
    @param:Autowired val repository: PersonRepository,
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
            ).satisfies(
                {
                    assertThat(it.dn?.get(3)).isEqualTo("uid=jane.smith")
                    assertThat(it.uid).isEqualTo("jane.smith")
                    assertThat(it.commonName).isEqualTo("Jane Smith")
                    assertThat(it.surname).isEqualTo("Smith")
                    assertThat(it.givenName).isEqualTo("Jane")
                },
                Index.atIndex(0),
            ).satisfies(
                {
                    assertThat(it.dn?.get(3)).isEqualTo("uid=${johnSmith.uid}")
                    assertThat(it.uid).isEqualTo(johnSmith.uid)
                    assertThat(it.commonName).isEqualTo(johnSmith.fullName)
                    assertThat(it.surname).isEqualTo(johnSmith.familyName)
                    assertThat(it.givenName).isEqualTo(johnSmith.givenName)
                },
                Index.atIndex(1),
            )
    }

    @Test
    @Order(2)
    fun find_by_id() {
        val name = PersonEntry.uidToName(johnSmith.uid)
        val personOpt = repository.findById(name)

        assertThat(personOpt).hasValueSatisfying {
            assertThat(it.dn?.get(0)).isEqualTo("dc=org")
            assertThat(it.dn?.get(1)).isEqualTo("dc=lafeuille")
            assertThat(it.dn?.get(2)).isEqualTo("ou=people")
            assertThat(it.dn?.get(3)).isEqualTo("uid=${johnSmith.uid}")

            assertThat(it.uid).isEqualTo(johnSmith.uid)
            assertThat(it.commonName).isEqualTo(johnSmith.fullName)
            assertThat(it.surname).isEqualTo(johnSmith.familyName)
            assertThat(it.givenName).isEqualTo(johnSmith.givenName)
        }
    }

    @Test
    @Order(3)
    fun save_and_check_id() {
        val person =
            PersonEntry(
                dn = null,
                uid = "john.doe",
                commonName = "John Doe",
                dateOfBirth = "2003030300Z",
                givenName = "John",
                surname = "Doe",
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
                assertThat(it.dn?.get(3)).isEqualTo("uid=john.doe")

                assertThat(it.uid).isEqualTo("john.doe")
                assertThat(it.commonName).isEqualTo("John Doe")
                assertThat(it.dateOfBirth).isEqualTo("2003030300Z")
                assertThat(it.surname).isEqualTo("Doe")
                assertThat(it.givenName).isEqualTo("John")
            },
        )
    }

    @Test
    @Order(4) // Ordering because we modify an entry that is checked by another test. Change for distinct dataset
    fun update() {
        val name = PersonEntry.uidToName(johnSmith.uid)
        val personOpt = repository.findById(name)

        assertThat(personOpt).isNotEmpty

        val person = personOpt.get()
        person.surname = CommonSurnames.JONES
        person.commonName = "${johnSmith.givenName} ${CommonSurnames.JONES}"

        val saved = repository.save(person)
        assertThat(saved).satisfies(
            Consumer {
                assertThat(it.isNew).isFalse()

                assertThat(it.dn?.get(0)).isEqualTo("dc=org")
                assertThat(it.dn?.get(1)).isEqualTo("dc=lafeuille")
                assertThat(it.dn?.get(2)).isEqualTo("ou=people")
                assertThat(it.dn?.get(3)).isEqualTo("uid=${johnSmith.uid}")

                assertThat(it.uid).isEqualTo(johnSmith.uid)
                assertThat(it.commonName).isEqualTo("${johnSmith.givenName} ${CommonSurnames.JONES}")
                assertThat(it.dateOfBirth).isEqualTo("2001010100Z")
                assertThat(it.surname).isEqualTo(CommonSurnames.JONES)
                assertThat(it.givenName).isEqualTo(johnSmith.givenName)
            },
        )
    }
}
