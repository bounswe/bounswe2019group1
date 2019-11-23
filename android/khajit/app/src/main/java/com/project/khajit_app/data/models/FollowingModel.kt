package com.project.khajit_app.data.models

data class FollowingModel( val id: Int,
                              val follower: Int,
                              val following: GenericUserModel)