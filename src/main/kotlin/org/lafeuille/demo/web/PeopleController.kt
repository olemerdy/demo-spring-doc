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

@RestController
@RequestMapping("api/v1/people")
class PeopleController(
    @Autowired val service: PersonService,
) {
    @GetMapping
    fun readPeople(
        pageable: Pageable,
        pagedResourcesAssembler: PagedResourcesAssembler<PersonResponse>,
    ): PagedModel<EntityModel<PersonResponse>> = pagedResourcesAssembler.toModel(service.getPeople(pageable))

    @GetMapping("{uid}")
    fun readPerson(
        @PathVariable uid: String,
    ): ResponseEntity<PersonResponse> =
        service.getPerson(uid)
            .map { ResponseEntity.ok(it) }
            .orElseGet { ResponseEntity.notFound().build() }

    @DeleteMapping("{uid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePerson(
        @PathVariable uid: String,
    ) {
        service.deletePerson(uid)
    }
}
