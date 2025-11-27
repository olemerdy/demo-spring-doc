package org.lafeuille.demo.data

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Index
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.lafeuille.demo.fixtures.CommonSurnames
import org.lafeuille.demo.fixtures.PersonFixtures.UnknownGuy
import org.lafeuille.demo.fixtures.PersonFixtures.charlesBrown
import org.lafeuille.demo.fixtures.PersonFixtures.jamesBrown
import org.lafeuille.demo.fixtures.PersonFixtures.johnSmith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.ldap.test.autoconfigure.DataLdapTest
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
    fun `find all is OK`() {
        val people = repository.findAll()
        assertThat(people)
            .hasSize(13)
            .allSatisfy(
                Consumer {
                    assertThat(it.dn?.get(0)).isEqualTo("dc=org")
                    assertThat(it.dn?.get(1)).isEqualTo("dc=lafeuille")
                    assertThat(it.dn?.get(2)).isEqualTo("ou=people")
                },
            ).satisfies(
                {
                    assertThat(it.dn?.get(3)).isEqualTo("uid=${charlesBrown.uid}")
                    assertThat(it.uid).isEqualTo(charlesBrown.uid)
                    assertThat(it.commonName).isEqualTo(charlesBrown.fullName)
                    assertThat(it.surname).isEqualTo(charlesBrown.familyName)
                    assertThat(it.givenName).isEqualTo(charlesBrown.givenName)
                },
                Index.atIndex(1),
            ).satisfies(
                {
                    assertThat(it.dn?.get(3)).isEqualTo("uid=${jamesBrown.uid}")
                    assertThat(it.uid).isEqualTo(jamesBrown.uid)
                    assertThat(it.commonName).isEqualTo(jamesBrown.fullName)
                    assertThat(it.surname).isEqualTo(jamesBrown.familyName)
                    assertThat(it.givenName).isEqualTo(jamesBrown.givenName)
                },
                Index.atIndex(3),
            ).satisfies(
                {
                    assertThat(it.dn?.get(3)).isEqualTo("uid=${johnSmith.uid}")
                    assertThat(it.uid).isEqualTo(johnSmith.uid)
                    assertThat(it.commonName).isEqualTo(johnSmith.fullName)
                    assertThat(it.surname).isEqualTo(johnSmith.familyName)
                    assertThat(it.givenName).isEqualTo(johnSmith.givenName)
                },
                Index.atIndex(4),
            )
    }

    @Test
    @Order(2)
    fun `find by id is OK`() {
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
    fun `find by unknow id is OK`() {
        val name = PersonEntry.uidToName(UnknownGuy.UID)
        val personOpt = repository.findById(name)
        assertThat(personOpt).isEmpty
    }

    @Test
    @Order(4)
    fun `save and check id`() {
        val person =
            PersonEntry(
                dn = null,
                uid = "john.doe",
                commonName = "John Doe",
                dateOfBirth = "2003030300Z",
                givenName = "John",
                surname = "Doe",
                countryOfCitizenship = "BE",
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
    @Order(5) // Ordering because we modify an entry that is checked by another test. Change for distinct dataset
    fun `update is OK`() {
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
                assertThat(it.dateOfBirth).isEqualTo("1580010600Z")
                assertThat(it.surname).isEqualTo(CommonSurnames.JONES)
                assertThat(it.givenName).isEqualTo(johnSmith.givenName)
            },
        )
    }
}
