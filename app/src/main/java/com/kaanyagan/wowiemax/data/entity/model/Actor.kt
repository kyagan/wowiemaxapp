package com.kaanyagan.wowiemax.data.entity.model

data class Actor(val id:Int, val name:String, val surname:String, val movieIds:List<ActorMovie>, val image:String)
