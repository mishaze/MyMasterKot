package com.example.mymaster.Models

import java.util.*

class RecordingSession {

    lateinit var end_service: String
    lateinit var start_service: String
    lateinit var date: String
    lateinit var id_master: String
    lateinit var id_client: String
    lateinit var price: String
    lateinit var service: String
    lateinit var day_week: String

    constructor(id_master: String, id_client: String, date: String, price: String, service: String, day_week: String, end_service: String, start_service: String) {
        this.id_master = id_master
        this.id_client = id_client
        this.date = date
        this.price = price
        this.service = service
        this.day_week = day_week
        this.end_service = end_service
        this.start_service = start_service
    }
    constructor() {
        this.id_master = null.toString()
        this.id_client = null.toString()
        this.date = null.toString()
        this.price = null.toString()
        this.service = null.toString()
        this.day_week = null.toString()
        this.end_service = null.toString()
        this.start_service = null.toString()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as RecordingSession
        return id_master == that.id_master &&
                id_client == that.id_client &&
                date == that.date &&
                price == that.price &&
                service == that.service &&
                day_week == that.day_week &&
                end_service == that.end_service &&
                start_service == that.start_service
    }

    override fun hashCode(): Int {
        return Objects.hash(id_master, id_client, date, price, service, day_week, end_service, start_service)
    }
}