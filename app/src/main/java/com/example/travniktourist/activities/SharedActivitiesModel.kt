package com.example.travniktourist.activities

import androidx.lifecycle.*

class SharedActivitiesModel : ViewModel() {

    private var activitiesRepository: ActivitiesRepository = ActivitiesRepository()

    val selectedActivities: MutableLiveData<Activities> = MutableLiveData()

    val activities: LiveData<List<Activities>> = liveData {
        val data = activitiesRepository.getActivities()
        emit(data)
    }
}