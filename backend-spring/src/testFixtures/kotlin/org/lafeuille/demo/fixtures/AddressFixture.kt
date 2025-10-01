package org.lafeuille.demo.fixtures

import org.lafeuille.demo.domain.PostalAddressResponse
import kotlin.String

data class AddressFixture(
    val addressCountry: String? = null,
    val addressLocality: String,
    val addressRegion: String? = null,
    val postOfficeBoxNumber: String? = null,
    val postalCode: String,
    val streetAddress: String,
) {
    fun toResponse() =
        PostalAddressResponse(
            addressCountry = addressCountry,
            addressLocality = addressLocality,
            addressRegion = addressRegion,
            postOfficeBoxNumber = postOfficeBoxNumber,
            postalCode = postalCode,
            streetAddress = streetAddress,
        )
}
