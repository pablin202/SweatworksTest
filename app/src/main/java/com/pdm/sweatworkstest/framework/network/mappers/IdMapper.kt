package com.pdm.sweatworkstest.framework.network.mappers

import com.pdm.sweatworkstest.core.domain.Id
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.network.responses.IdResponse
import javax.inject.Inject

class IdMapper
@Inject
constructor() : EntityMapper<IdResponse, Id> {
    override fun mapFromEntity(entity: IdResponse): Id = Id(entity.name, entity.value)

    override fun mapToEntity(domainModel: Id): IdResponse {
        TODO("Not yet implemented")
    }
}