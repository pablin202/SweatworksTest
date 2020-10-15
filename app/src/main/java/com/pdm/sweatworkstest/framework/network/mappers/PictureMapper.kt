package com.pdm.sweatworkstest.framework.network.mappers

import com.pdm.sweatworkstest.core.domain.Picture
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.network.responses.PictureResponse
import javax.inject.Inject

class PictureMapper
@Inject
constructor() : EntityMapper<PictureResponse, Picture> {
    override fun mapFromEntity(entity: PictureResponse): Picture =
        Picture(entity.large, entity.medium, entity.thumbnail)

    override fun mapToEntity(domainModel: Picture): PictureResponse {
        TODO("Not yet implemented")
    }
}