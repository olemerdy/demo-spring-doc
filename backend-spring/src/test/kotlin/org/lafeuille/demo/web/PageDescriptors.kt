package org.lafeuille.demo.web

import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName

object PageDescriptors {
    object Parameter {
        val PAGE: ParameterDescriptor by lazy {
            parameterWithName("page")
                .description("Page number, starting at 0")
                .optional()
        }
        val SIZE: ParameterDescriptor by lazy {
            parameterWithName("size")
                .description("Page size to be returned")
                .optional()
        }
        val SORT: ParameterDescriptor by lazy {
            parameterWithName("sort")
                .description("Property name and and optional direction (asc or desc) separated by a comma. Multiple values possible")
                .optional()
        }
    }

    object Parameters {
        val ALL: List<ParameterDescriptor> by lazy {
            listOf(Parameter.PAGE, Parameter.SIZE, Parameter.SORT)
        }
    }
}
