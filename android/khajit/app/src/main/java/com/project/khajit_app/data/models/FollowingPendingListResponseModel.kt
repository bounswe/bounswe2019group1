package com.project.khajit_app.data.models

data class FollowingPendingListResponseModel(val id: Int,
                                             val follower: Int,
                                             val is_active: Boolean,
                                             val following: GenericUserModel)