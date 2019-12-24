package com.project.khajit_app.data.annotationModels

data class ShowTextAnnotationModel(val id : String,
                                   val creator : CreatorModel,
                                   val body: List<BodyModel>,
                                   var annotatedText : String,
                                   val startChar : Int,
                                   val endChar : Int,
                                   var bodyFinal : String,
                                   var name : String )