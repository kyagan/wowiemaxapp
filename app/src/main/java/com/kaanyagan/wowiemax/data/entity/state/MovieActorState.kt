package com.kaanyagan.wowiemax.data.entity.state

import com.kaanyagan.wowiemax.data.entity.model.Actor

sealed class MovieActorState {
    object Idle : MovieActorState()
    object Loading : MovieActorState()
    class Result(val actors: List<Actor>) : MovieActorState()
    class Error(val throwable: Throwable) : MovieActorState()
}