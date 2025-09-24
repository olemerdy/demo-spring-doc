package org.lafeuille.demo.domain

// @see https://schema.org/PostalAddress
data class PostalAddressResponse(
    val addressCountry: String? = null,
    val addressLocality: String? = null,
    val addressRegion: String? = null,
    val postOfficeBoxNumber: String? = null,
    val postalCode: String? = null,
    val streetAddress: String? = null,
)
