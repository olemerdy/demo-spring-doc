package org.lafeuille.demo.fixtures

object AddressFixtures {
    val overTheRainbow by lazy {
        AddressFixture(
            streetAddress = "123 Over the Rainbow Road",
            postalCode = "85022",
            addressLocality = "Phoenix",
            addressRegion = "Arizona",
        )
    }
}
