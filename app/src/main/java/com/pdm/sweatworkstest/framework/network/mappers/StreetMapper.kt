package com.pdm.sweatworkstest.framework.network.mappers

import com.pdm.sweatworkstest.core.domain.Street
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.network.responses.StreetResponse
import javax.inject.Inject

class StreetMapper
@Inject
constructor(): EntityMapper<StreetResponse, Street> {
    override fun mapFromEntity(entity: StreetResponse): Street = Street(entity.number, entity.name)

    override fun mapToEntity(domainModel: Street): StreetResponse {
        TODO("Not yet implemented")
    }
}