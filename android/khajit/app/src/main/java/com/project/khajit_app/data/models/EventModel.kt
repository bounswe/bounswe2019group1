package com.project.khajit_app.data.models

import java.io.Serializable
data class EventModel ( val title: String,
                        val country: String,
                        val date: String,
                        val impact: String,
                        val forecast: String,
                        val previous: String):Serializable