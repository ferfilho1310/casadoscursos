package br.com.casadoscursos.repository.monitoringclickusers

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent

class CoursesMonitoring : CoursesMonitoringContract {

    override fun monitoring(course: String, context: Context) {
        val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, course)
        }
    }
}