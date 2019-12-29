package com.project.khajit_app.data.annotationModels

data class SelectorModel(val refinedBy : RefinedByModel,
                         val type : String,
                         val value : String)


/*"selector": {
    "refinedBy": {
    "type": "TextPositionSelector",
    "start": 6,
    "end": 27
},
    "type": "FragmentSelector",
    "value": "xpointer(/doc/body/section[2]/para[1])"
},*/