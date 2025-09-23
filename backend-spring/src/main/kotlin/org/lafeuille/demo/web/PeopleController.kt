package org.lafeuille.demo.web

import org.lafeuille.demo.domain.PersonResponse
import org.lafeuille.demo.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.Locale

@RestController
@RequestMapping("api/v1/people")
class PeopleController(
    @param:Autowired val service: PersonService,
) {
    @GetMapping
    fun readPeople(
        pageable: Pageable,
        locale: Locale,
        pagedResourcesAssembler: PagedResourcesAssembler<PersonResponse>,
    ): PagedModel<EntityModel<PersonResponse>> = pagedResourcesAssembler.toModel(service.getPeople(pageable, locale))

    @GetMapping("{uid}")
    fun readPerson(
        @PathVariable uid: String,
        locale: Locale,
    ): ResponseEntity<PersonResponse> = ResponseEntity.of(service.getPerson(uid, locale))

    @DeleteMapping("{uid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePerson(
        @PathVariable uid: String,
    ) {
        service.deletePerson(uid)
    }
}
