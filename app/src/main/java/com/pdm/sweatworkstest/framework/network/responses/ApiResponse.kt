package com.pdm.sweatworkstest.framework.network.responses

import com.google.gson.annotations.SerializedName

data class ApiResponse(@SerializedName("results") val results : List<UserResponse>)