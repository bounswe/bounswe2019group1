package com.project.khajit_app.data.models

data class FollowModel(val id: Int,
                       val follower: Int,
                       val following: GenericUserModel)