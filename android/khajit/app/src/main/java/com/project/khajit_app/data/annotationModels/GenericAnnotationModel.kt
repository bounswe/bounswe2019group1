package com.project.khajit_app.data.annotationModels

data class GenericAnnotationModel (
    val id: String?,
    val creator : CreatorModel?,
    val body : List<BodyModel>? ,
    val target : TargetModel?,
    val type : String?,
    val motivation : String?,
    val created : String?
)