package com.pdm.sweatworkstest.framework.network.mappers

import com.pdm.sweatworkstest.core.domain.Coordinates
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.network.responses.CoordinatesResponse
import javax.inject.Inject

class CoordinatesMapper
@Inject
constructor() : EntityMapper<CoordinatesResponse, Coordinates> {
    override fun mapFromEntity(entity: CoordinatesResponse): Coordinates = Coordinates(entity.latitude, entity.longitude)

    override fun mapToEntity(domainModel: Coordinates): CoordinatesResponse {
        TODO("Not yet implemented")
    }

}