package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleList
import com.example.domain.Domain.repository.ScheduleRepository
import java.text.SimpleDateFormat
import java.util.*


class GetScheduleListUseCase(private val scheduleRepository: ScheduleRepository) {

    private val date = getCurrentDateTime()
    val dateInString = date.toString("yyyy.MM.dd")

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun execute(callback: FirebaseCallback<ResponseScheduleList>) {


        scheduleRepository.getSchedule(object : FirebaseCallback<ResponseScheduleList> {
            override fun onResponse(response: ResponseScheduleList) {
                val answer = ResponseScheduleList()
                response.answer.forEach {
                    if (dateInString <= it.date.toString()) {
                        answer.answer.add(it)
                    }
                }
                callback.onResponse(answer)
            }
        })
    }
}