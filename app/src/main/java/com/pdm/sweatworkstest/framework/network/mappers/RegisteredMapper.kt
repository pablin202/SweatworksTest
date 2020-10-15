package com.pdm.sweatworkstest.framework.network.mappers

import com.pdm.sweatworkstest.core.domain.Registered
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.network.responses.RegisteredResponse
import javax.inject.Inject

class RegisteredMapper
@Inject
constructor() : EntityMapper<RegisteredResponse, Registered> {
    override fun mapFromEntity(entity: RegisteredResponse): Registered =
        Registered(entity.age, entity.date)

    override fun mapToEntity(domainModel: Registered): RegisteredResponse {
        TODO("Not yet implemented")
    }
}