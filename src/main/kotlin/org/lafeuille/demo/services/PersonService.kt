package org.lafeuille.demo.services

import org.lafeuille.demo.data.Person
import org.lafeuille.demo.data.PersonRepository
import org.lafeuille.demo.domain.PersonResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class PersonService(
    private val repository: PersonRepository,
) {
    fun getPeople(pageable: Pageable): Page<PersonResponse> =
        // Spring Data LDAP does not handle pagination
        PageImpl(repository.findAll(), pageable, repository.count())
            .map { it.toResponse() }

    fun getPerson(uid: String): Optional<PersonResponse> =
        repository.findById(Person.uidToName(uid))
            .map { it.toResponse() }

    @Transactional
    fun deletePerson(uid: String) {
        repository.deleteById(Person.uidToName(uid))
    }
}
