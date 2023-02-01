package com.example.pickapp.repo

import androidx.lifecycle.LiveData
import com.example.pickapp.Category
import com.example.pickapp.dto.Reply

interface Repo {
    var data:List<Category>
    //var dataReply: Reply?
    fun getAsync(callback: Callback<Reply>, category:String)
    //fun save(cardInfo: CardInfo, callback: Callback<CardInfo>)

    interface Callback<Reply> {
        fun onSuccess(reply: Reply) {}
        fun onError(e: Exception) {}
    }
}