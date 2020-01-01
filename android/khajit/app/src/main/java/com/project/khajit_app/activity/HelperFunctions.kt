package com.project.khajit_app.activity

import java.util.regex.Pattern

class HelperFunctions {
    fun validIban(iban: String): Boolean{
        if(iban.length != 16) {
            return false
        }else {
            return true
        }
    }

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    fun isTitleValid(title : String):Boolean{
        if(title ==""){
            return false
        }else{
            return true
        }
    }

    fun isCommentValid(comment : String):Boolean{
        if(comment ==""){
            return false
        }else{
            return true
        }
    }

    fun isAnnotationValid(annotation: String):Boolean{
        if(annotation ==""){
            return false
        }else{
            return true
        }
    }
    fun isAnnotationStartEndValid(Start: Int,End: Int):Boolean{
        if(Start>=End){
            return false
        }else{
            return true
        }
    }
}