package com.eduardo.kotlinudemydelivery.Providers

import com.eduardo.kotlinudemydelivery.api.ApiRoutes
import com.eduardo.kotlinudemydelivery.models.Category
import com.eduardo.kotlinudemydelivery.models.ResponseHttp
import com.eduardo.kotlinudemydelivery.models.User
import com.eduardo.kotlinudemydelivery.routes.UsersRoutes
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class UsersProvider(val token: String? = null) {

    private var usersRoutes: UsersRoutes? = null
    private var usersRoutesToken: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUserRoutes()
        if (token != null) {
            usersRoutesToken = api.getUserRoutesWithToken(token)
        }
    }

    fun getDelivery(): Call<ArrayList<User>>?{
        return usersRoutesToken?.getDelivery(token!!)
    }

    fun register(user: User): Call<ResponseHttp>? {
        return usersRoutes?.register(user)
    }

    fun login(email: String, password: String): Call<ResponseHttp>? {
        return usersRoutes?.login(email, password)
    }

    fun updateWithoutImage(user: User): Call<ResponseHttp>? {
        return usersRoutesToken?.updateWithoutImage(user, token!!)
    }

    fun update(file: File, user: User): Call<ResponseHttp>? {
        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        val image = MultipartBody.Part.createFormData("image",file.name, reqFile)
        val requestBody = RequestBody.create(MediaType.parse("text/plain"), user.toJson())

        return  usersRoutesToken?.update(image,requestBody, token!!)
    }
}