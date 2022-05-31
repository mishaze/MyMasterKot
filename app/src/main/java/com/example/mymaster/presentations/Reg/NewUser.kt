package com.example.mymaster.presentations.Reg

import com.example.domain.Domain.models.*
import com.google.firebase.auth.FirebaseAuth

class NewUser(
    private val name: String,
    private val email: String
) {

    val userInformation = UserInformation(
        uid = FirebaseAuth.getInstance().currentUser!!.uid,
        name = name,
        surname = "",
        phone_number = "",
        specialization = "",
        legal_information = "",
        email = email,
        master_info = "",
        address = "",
        definition = ""
    )

    private val dayWeekList: ArrayList<DayWeek> = ArrayList()
    lateinit var scheduleSetting: ScheduleSettingModel
    val servicesModel = ServicesModel(
        name = "",
        price = "",
        timeInWork = "",
        info = "",
        uidServices = "",
        status = true
    )

    fun createNewUser() {

        userInformation.email = email
        userInformation.name = name
        userInformation.uid = FirebaseAuth.getInstance().uid


        dayWeekList.add(
            DayWeek(
                monday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                tuesday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                wednesday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                thursday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                friday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                sunday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                saturday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                )
            )
        )

        dayWeekList.add(
            DayWeek(
                monday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                tuesday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                wednesday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                thursday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                friday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                sunday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                ),
                saturday = Day(
                    start = "08:00",
                    end = "23:00",
                    start_dinner = "00:00",
                    end_dinner = "00:00"
                )
            )
        )

        scheduleSetting = ScheduleSettingModel(time = "01:50", num = 1, dayOfWeek = dayWeekList)
    }


}