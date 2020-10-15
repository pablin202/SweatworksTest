package com.pdm.sweatworkstest.framework.network.mappers

import com.pdm.sweatworkstest.core.domain.User
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.network.responses.*
import javax.inject.Inject

class UserMapper
@Inject
constructor(
    private val dobMapper: DobMapper,
    private val idMapper: IdMapper,
    private val locationMapper: LocationMapper,
    private val loginMapper: LoginMapper,
    private val nameMapper: NameMapper,
    private val pictureMapper: PictureMapper,
    private val registeredMapper: RegisteredMapper
) : EntityMapper<UserResponse, User> {
    override fun mapFromEntity(entity: UserResponse): User {
        return User(
            entity.cell,
            dobMapper.mapFromEntity(entity.dob),
            entity.email,
            entity.gender,
            idMapper.mapFromEntity(entity.id),
            locationMapper.mapFromEntity(entity.location),
            loginMapper.mapFromEntity(entity.login),
            nameMapper.mapFromEntity(entity.name),
            entity.nat,
            entity.phone,
            pictureMapper.mapFromEntity(entity.picture),
            registeredMapper.mapFromEntity(entity.registered)
        )
    }

    override fun mapToEntity(domainModel: User): UserResponse {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(entities: List<UserResponse>): List<User>{
        return entities.map { mapFromEntity(it) }
    }
}