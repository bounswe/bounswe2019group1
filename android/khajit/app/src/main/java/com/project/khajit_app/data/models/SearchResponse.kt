package com.project.khajit_app.data.models

data class SearchResponse( val count : Int, val next : String?, val previous : String?, val results: List<GenericUserModel>)