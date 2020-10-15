package com.pdm.sweatworkstest.framework.network.mappers

import com.pdm.sweatworkstest.core.domain.Dob
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.network.responses.DobResponse
import javax.inject.Inject

class DobMapper @Inject
constructor() : EntityMapper<DobResponse, Dob> {
    override fun mapFromEntity(entity: DobResponse): Dob = Dob(entity.age, entity.date)

    override fun mapToEntity(domainModel: Dob): DobResponse {
        TODO("Not yet implemented")
    }
}