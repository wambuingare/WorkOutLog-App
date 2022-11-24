package dev.nancygym.workoutlog.models

data class LoginResponse(
    var message:String,
    @SerializedName("access_token")var accesstoken: String,
    @SerializedName("user_id")var userId: String,
    @SerializedName("profile_id")var profileId: String
)
