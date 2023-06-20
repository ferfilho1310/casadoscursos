package br.com.casadoscursos.repository.monitoringclickusers

import android.content.Context

interface CoursesMonitoringContract {

    fun monitoring(course: String, context: Context)
}