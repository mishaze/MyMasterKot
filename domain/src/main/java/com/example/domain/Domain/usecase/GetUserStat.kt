package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.StatModel
import com.example.domain.Domain.models.responses.*
import com.example.domain.Domain.repository.ScheduleRepository
import com.example.domain.Domain.repository.UserInformationRepository
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class GetUserStat(
    private val scheduleRepository: ScheduleRepository,
    private val userInformationRepository: UserInformationRepository
) {

    val decimalFormat = DecimalFormat("###,###")


    val userStat = ResponseUserStat()

    private val date = getCurrentDateTime()
    val dateInString = date.toString("yyyy.MM.dd")
    val monthInString = date.toString("yyyy.MM")
    val timeInString = date.toString("HH:mm")

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun execute(callback: FirebaseCallback<ResponseUserStat>) {
        scheduleRepository.getSchedule(object : FirebaseCallback<ResponseScheduleList> {
            override fun onResponse(response: ResponseScheduleList) {
                userStat.answer?.allServices = response.answer.size.toString()

                var result = 0
                response.answer.forEach {
                    result += it.price?.toInt()!!
                }
                userStat.answer?.allIncome = decimalFormat.format(result)

                result = 0
                response.answer.forEach {
                    if (dateInString > it.date.toString())
                        result += 1
                }
                userStat.answer?.completeServices = decimalFormat.format(result)

                val datemm = "$monthInString.01"
                result = 0
                response.answer.forEach {
                    if (datemm < it.date.toString())
                        result += it.price?.toInt()!!
                }

                userStat.answer?.incomeOfMonth = decimalFormat.format(result)


                userInformationRepository.getUserInformation(object :
                    FirebaseCallback<ResponseUserInformation> {
                    override fun onResponse(response: ResponseUserInformation) {
                        userStat.answer?.definition = "${response.answer?.definition}/10"
                        callback.onResponse(userStat)
                    }
                })
            }
        })

    }
}