package com.kaanyagan.wowiemax.data.entity.state

import com.kaanyagan.wowiemax.data.entity.model.Category

sealed class CategoryListState{
    object Idle:CategoryListState()
    object Empty:CategoryListState()
    class Result(val categories:Array<Category>):CategoryListState()
    class Error(val throwable: Throwable):CategoryListState()
}
