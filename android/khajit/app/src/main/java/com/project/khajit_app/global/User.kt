package com.project.khajit_app.global

object User {
    var token: String? = ""
    var id: Int? = 0
    var username: String? = ""
    var email: String? = ""
    var first_name: String? = ""
    var last_name: String? = ""
    var type: Boolean? = false   // if the user is trader type info will be true otherwise user is basic and type info will be false
    var title: String? = ""
    var bio: String? = ""
    var location: String? = ""
    var phone_number: String? = ""
    var iban_number: String? = ""
    var is_public: Boolean? = true

    var whereIamAsId: Int? = 0  // DONT USE THIS
}