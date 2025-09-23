package org.lafeuille.demo.web

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath

object CountryDescriptors {
    object Field {
        val IDENTIFIER: FieldDescriptor by lazy {
            fieldWithPath("identifier")
                .description("Unique identifier, 2-letters code following ISO 3166-2")
        }
        val NAME: FieldDescriptor by lazy {
            fieldWithPath("name")
                .description("Country full name displayed in requested locale")
        }
    }

    object Fields {
        val ALL: List<FieldDescriptor> by lazy {
            listOf(
                Field.IDENTIFIER,
                Field.NAME,
            )
        }
    }
}
