package com.pdm.sweatworkstest.framework.network.mappers

import com.pdm.sweatworkstest.core.domain.Name
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.network.responses.NameResponse
import javax.inject.Inject

class NameMapper
@Inject
constructor() : EntityMapper<NameResponse, Name> {
    override fun mapFromEntity(entity: NameResponse): Name =
        Name(entity.first, entity.last, entity.title)

    override fun mapToEntity(domainModel: Name): NameResponse {
        TODO("Not yet implemented")
    }
}