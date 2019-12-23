package com.project.khajit_app.data.annotationModels

data class CreateAnnotationModel (
    val creator : String,
    val body : List<BodyModel> ,
    val target : TargetModel,
    val type : String,
    val motivation : String,
    val created : String

)


//{
    //"@context": "http://www.w3.org/ns/annao.aaa",
    //"creator": "http://example.org/abdullah21",
    /*"body": [
    {
        "type": "TextualBody",
        "purpose": "tagging",
        "value": "love"
    },
    {
        "type": "Choice",
        "purpose": "",
        "value": ""
    }
    ],*/
    /*"target": {
    "selector": {
        "refinedBy": {
        "type": "TextPositionSelector",
        "start": 6,
        "end": 27
    },
        "type": "FragmentSelector",
        "value": "xpointer(/doc/body/section[2]/para[1])"
    },
    "type": "Image",
    "styleClass": "mystyle",
    "source": "http://example.com/image1",
    "image_id": "http://example.com/image1#xywh=100,100,300,300"
},*/
   // "type": "Annotation",
   // "motivation": "commenting",
   // "created": "2015-10-13T13:00:00Z"
//}