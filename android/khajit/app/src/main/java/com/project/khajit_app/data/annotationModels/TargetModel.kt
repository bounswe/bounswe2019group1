package com.project.khajit_app.data.annotationModels

data class TargetModel (val selector : SelectorModel,
                        val type : String,
                        val styleClass : String,
                        val source : String,
                        val image_id : String
                        )


//"target": {
/*"selector": {
    "refinedBy": {
    "type": "TextPositionSelector",
    "start": 6,
    "end": 27
},
    "type": "FragmentSelector",
    "value": "xpointer(/doc/body/section[2]/para[1])"
},*/
//"type": "Image",
//"styleClass": "mystyle",
//"source": "http://example.com/image1",
//"image_id": "http://example.com/image1#xywh=100,100,300,300"
//},