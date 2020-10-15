package com.pdm.sweatworkstest.framework.network.responses

data class LocationResponse(
    val city: String,
    val coordinates: CoordinatesResponse,
    val postcode: String,
    val state: String,
    val street: StreetResponse,
    val timezone: TimezoneResponse
)