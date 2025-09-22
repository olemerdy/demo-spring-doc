package org.lafeuille.demo.data

import org.springframework.data.ldap.repository.LdapRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(exported = false)
interface PersonRepository : LdapRepository<PersonEntry>
