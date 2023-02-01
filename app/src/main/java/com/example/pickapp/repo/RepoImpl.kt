package com.example.pickapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.pickapp.Category
import com.example.pickapp.api.Api
import com.example.pickapp.dao.CategoriesDao
import com.example.pickapp.dto.Reply
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoImpl(
    //val dao :CategoriesDao
) :Repo {
    override var data: List<Category> =listOf(
        Category("backgrounds"),
        Category("fashion"),
        Category("nature"),
        Category("feelings"),
        Category("places"),
        Category("animals"),
        Category("travel")
    )

    //override var dataReply: Reply? = null

    override fun getAsync(callback: Repo.Callback<Reply>, category:String) {
        Api.retrofitService.getAll(category).enqueue(object : Callback<Reply> {
            override fun onResponse(call: Call<Reply>, response: Response<Reply>) {
                if (!response.isSuccessful) {
                    callback.onError(RuntimeException(response.message()))
                    return
                }

                callback.onSuccess(response.body() ?: throw java.lang.RuntimeException("body is null")).also {
                    //dataReply = response.body()
                }
            }

            override fun onFailure(call: Call<Reply>, t: Throwable) {
                //
            }
        })
    }
}