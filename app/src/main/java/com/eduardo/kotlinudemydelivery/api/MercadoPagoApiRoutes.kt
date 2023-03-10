package com.eduardo.kotlinudemydelivery.api

import com.eduardo.kotlinudemydelivery.routes.*

class MercadoPagoApiRoutes {

    val API_URL = "https://api.mercadopago.com/"

    val retrofit = RetrofitClient()

    fun getMercadoPagoRoutes(): MercadoPagoRoutes {
        return retrofit.getClient(API_URL).create(MercadoPagoRoutes::class.java)
    }


}