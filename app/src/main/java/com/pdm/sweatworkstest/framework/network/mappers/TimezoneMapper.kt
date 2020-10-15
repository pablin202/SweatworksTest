package com.pdm.sweatworkstest.framework.network.mappers

import com.pdm.sweatworkstest.core.domain.Timezone
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.network.responses.TimezoneResponse
import javax.inject.Inject

class TimezoneMapper
@Inject
constructor(): EntityMapper<TimezoneResponse, Timezone>{
    override fun mapFromEntity(entity: TimezoneResponse): Timezone = Timezone(entity.description, entity.offset)
    override fun mapToEntity(domainModel: Timezone): TimezoneResponse {
        TODO("Not yet implemented")
    }
}