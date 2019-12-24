package com.project.khajit_app.data.annotationModels

data class ShowImageAnnotationModel(val creator : CreatorModel,
                                    val body: List<BodyModel>,
                                    val annotatedText : String,
                                    val startChar : Int,
                                    val endChar : Int)