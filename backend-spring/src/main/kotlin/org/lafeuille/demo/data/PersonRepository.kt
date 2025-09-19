package org.lafeuille.demo.data

import org.springframework.data.ldap.repository.LdapRepository
import org.springframework.data.rest.core.annotation.RestResource

@RestResource(exported = false)
interface PersonRepository : LdapRepository<PersonEntry>
