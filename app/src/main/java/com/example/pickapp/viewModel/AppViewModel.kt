package com.example.pickapp.viewModel

import FeedModel
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pickapp.SingleLiveEvent
import com.example.pickapp.api.Api
import com.example.pickapp.dao.CategoriesDao
import com.example.pickapp.dto.Reply
import com.example.pickapp.repo.Repo
import com.example.pickapp.repo.RepoImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppViewModel(
    application: Application
) : AndroidViewModel(application),CategoriesDao {
    val repo: Repo = RepoImpl()
    val data by repo::data
    //val data1 by repo::dataReply
    var currentReply: Reply? = null
    val dataReply = MutableLiveData(FeedModel())
    val clickedCategory = SingleLiveEvent<Array<String>?>()

    override fun clicked(value: String) {


        repo.getAsync(object : Repo.Callback<Reply> {
           //@RequiresApi(Build.VERSION_CODES.TIRAMISU)
            override fun onSuccess(reply:Reply) {
                currentReply = reply
               val urls: List<String> = reply.hits.map { it.previewURL!! }

               dataReply.postValue(FeedModel(reply = reply, loading = true, empty = reply.hits.isEmpty()))
                clickedCategory.value = urls.toTypedArray()

            }


            override fun onError(e: Exception) {
                dataReply.postValue(FeedModel(error = true))
            }
        },value)
    }

}