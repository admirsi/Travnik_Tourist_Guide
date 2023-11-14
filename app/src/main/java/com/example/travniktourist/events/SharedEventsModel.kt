package com.example.travniktourist.events

import androidx.lifecycle.*

class SharedEventsModel : ViewModel() {

    private var eventsRepository: EventsRepository = EventsRepository()

    val selectedEvents: MutableLiveData<Events> = MutableLiveData()

    val events: LiveData<List<Events>> = liveData {
        val data = eventsRepository.getEvents()
        emit(data)
    }
}