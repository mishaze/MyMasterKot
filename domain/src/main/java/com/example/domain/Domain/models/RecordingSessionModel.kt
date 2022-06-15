package com.example.domain.Domain.models

data class RecordingSessionModel(
    var date: String?= "",
    var time: String?="",
    var uids :String? = "",
    var uid :String? ="",
    var status: Boolean? = true,
    var price: String? ="",
    var uidR: String? = "",
    var name: String? = ""
)