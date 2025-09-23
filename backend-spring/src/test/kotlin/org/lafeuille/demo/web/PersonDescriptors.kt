package org.lafeuille.demo.web

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.beneathPath
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath
import org.springframework.restdocs.payload.PayloadSubsectionExtractor
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName

object PersonDescriptors {
    object Parameter {
        val UID: ParameterDescriptor by lazy {
            parameterWithName("uid")
                .description("Unique identifier")
        }
    }

    object Field {
        val IDENTIFIER: FieldDescriptor by lazy {
            fieldWithPath("identifier")
                .description("Unique identifier")
        }
        val NAME: FieldDescriptor by lazy {
            fieldWithPath("name")
                .description("Full name")
        }
        val GIVEN_NAME: FieldDescriptor by lazy {
            fieldWithPath("givenName")
                .description("Given name")
        }
        val FAMILY_NAME: FieldDescriptor by lazy {
            fieldWithPath("familyName")
                .description("Family name")
        }
        val BIRTHDATE: FieldDescriptor by lazy {
            fieldWithPath("birthDate")
                .description("Birth date, in ISO-8601 format")
        }
        val NATIONALITY: FieldDescriptor by lazy {
            subsectionWithPath("nationality")
                .description("Country of citizenship")
        }
    }

    object SubSection {
        val NATIONALITY: PayloadSubsectionExtractor<*> by lazy {
            beneathPath("nationality")
        }
    }

    object Fields {
        val ALL: List<FieldDescriptor> by lazy {
            listOf(
                Field.IDENTIFIER,
                Field.NAME,
                Field.GIVEN_NAME,
                Field.FAMILY_NAME,
                Field.BIRTHDATE,
                Field.NATIONALITY,
            )
        }
    }
}
