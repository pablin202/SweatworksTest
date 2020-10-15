package com.pdm.sweatworkstest.framework.network.mappers

import com.pdm.sweatworkstest.core.domain.Location
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.network.responses.CoordinatesResponse
import com.pdm.sweatworkstest.framework.network.responses.LocationResponse
import com.pdm.sweatworkstest.framework.network.responses.TimezoneResponse
import javax.inject.Inject

class LocationMapper
@Inject
constructor(
    private val coordinatesMapper: CoordinatesMapper,
    private val timezoneMapper: TimezoneMapper,
    private val streetMapper: StreetMapper
) : EntityMapper<LocationResponse, Location> {
    override fun mapFromEntity(entity: LocationResponse): Location {
        return Location(
            city = entity.city,
            coordinates = coordinatesMapper.mapFromEntity(entity.coordinates),
            postcode = entity.postcode,
            state = entity.state,
            street = streetMapper.mapFromEntity(entity.street),
            timezone = timezoneMapper.mapFromEntity(entity.timezone)
        )
    }

    override fun mapToEntity(domainModel: Location): LocationResponse {
        TODO("Not yet implemented")
    }

}