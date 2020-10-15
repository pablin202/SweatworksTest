package com.pdm.sweatworkstest.framework.cache.mappers

import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.cache.entities.FavoriteUserEntity
import javax.inject.Inject

class FavoriteUserMapper
@Inject
constructor() : EntityMapper<FavoriteUserEntity, FavoriteUser> {

    override fun mapFromEntity(entity: FavoriteUserEntity): FavoriteUser = FavoriteUser(
        entity.uuid,
        entity.username,
        entity.titleName,
        entity.firstName,
        entity.lastName,
        entity.email,
        entity.phone,
        entity.largeImage,
        entity.mediumImage,
        entity.thumbnailImage
    )

    override fun mapToEntity(domainModel: FavoriteUser): FavoriteUserEntity = FavoriteUserEntity(
        domainModel.uuid,
        domainModel.titleName,
        domainModel.username,
        domainModel.firstName,
        domainModel.lastName,
        domainModel.email,
        domainModel.phone,
        domainModel.largeImage,
        domainModel.mediumImage,
        domainModel.thumbnailImage
    )

    fun mapFromEntityList(entities: List<FavoriteUserEntity>): List<FavoriteUser>{
        return entities.map { mapFromEntity(it) }
    }

}