package org.lafeuille.demo.data

import org.springframework.data.ldap.repository.LdapRepository

interface PersonRepository : LdapRepository<Person>