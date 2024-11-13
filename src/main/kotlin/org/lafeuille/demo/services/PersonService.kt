package org.lafeuille.demo.services

import org.lafeuille.demo.data.PersonRepository
import org.lafeuille.demo.domain.PersonResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional
import javax.naming.Name

@Service
open class PersonService(
    private val repository: PersonRepository,
) {
    fun getPeople(pageable: Pageable): Page<PersonResponse> = Page.empty()

    fun getPerson(id: Name): Optional<PersonResponse> =
        repository.findById(id)
            .map { it.toResponse() }

    @Transactional
    open fun deletePerson(id: Name) {
        repository.deleteById(id)
    }
}
