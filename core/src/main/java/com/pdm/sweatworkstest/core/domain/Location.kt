package com.pdm.sweatworkstest.core.domain

data class Location(
    val city: String,
    val coordinates: Coordinates,
    val postcode: String,
    val state: String,
    val street: Street,
    val timezone: Timezone
)