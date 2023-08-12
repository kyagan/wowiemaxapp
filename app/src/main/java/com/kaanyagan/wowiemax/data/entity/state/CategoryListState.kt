package com.kaanyagan.wowiemax.data.entity.state

import com.kaanyagan.wowiemax.data.entity.model.Categorie

sealed class CategoryListState{
    object Idle:CategoryListState()
    object Empty:CategoryListState()
    class Result(val categories:Array<Categorie>):CategoryListState()
    class Error(val throwable: Throwable):CategoryListState()
}
