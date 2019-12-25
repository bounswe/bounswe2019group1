package com.project.khajit_app.data.models

import java.io.Serializable

data class GeneralNotificationModel(val id : Int?,
                                    val text : String?,
                                    val date : String?,
                                    val is_pending : Boolean?,
                                    val is_active : Boolean?,
                                    val owner : Int?): Serializable

