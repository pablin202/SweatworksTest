package com.pdm.sweatworkstest.framework.network.mappers

import com.pdm.sweatworkstest.core.domain.Login
import com.pdm.sweatworkstest.core.domain.util.EntityMapper
import com.pdm.sweatworkstest.framework.network.responses.LoginResponse
import javax.inject.Inject

class LoginMapper
@Inject
constructor() : EntityMapper<LoginResponse, Login> {
    override fun mapFromEntity(entity: LoginResponse): Login = Login(
        entity.md5,
        entity.password,
        entity.salt,
        entity.sha1,
        entity.sha256,
        entity.username,
        entity.uuid
    )

    override fun mapToEntity(domainModel: Login): LoginResponse {
        TODO("Not yet implemented")
    }
}