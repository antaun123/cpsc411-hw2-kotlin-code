package org.csuf.cpsc411.simplehttpclient

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import java.lang.reflect.Type

class ClaimService (val ctx : CustomActivity){

    var claimList : MutableList<Claim> = mutableListOf()
    var currentIndx : Int = 0

    companion object {
        private var cService : ClaimService? = null

        fun getInstance(act : CustomActivity) : ClaimService {
            if (cService == null) {
                cService = ClaimService(act)
            }
            return cService!!
        }
    }

    fun next() : Claim {
        currentIndx = currentIndx + 1
        return claimList[currentIndx]
    }

    fun isLastObject() : Boolean  {
        if (currentIndx == claimList.count()-1) return true
        return false
    }

    fun fetchAt(e : Int) : Claim {
        currentIndx = e
        return claimList[currentIndx]
    }

    inner class GetAllServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            // JSON string
            if (responseBody != null) {
                Log.d("Claim Service", "The response JSON string is ${String(responseBody)}")
                val gson = Gson()
                val claimListType: Type = object : TypeToken<List<Claim>>() {}.type
                claimList = gson.fromJson(String(responseBody), claimListType)
                if (claimList.isEmpty()) {
                    Log.d("getAll","claim list is empty")
                }
                else { //if not claimList not empty
                    ctx.refreshScreen(claimList[currentIndx])
                }
                //act.runOnUiThread {
                //    cbLambdaFunction()
                //}
                Log.d("Claim Service", "The Claim List: ${claimList}")
            }
            else {
                Log.d("Claim Service", "Using getAll(), no Claims found!")
            }
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            //do nothing
        }
    }

    inner class AddServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            if (responseBody != null) {
                val respStr = String(responseBody)
                Log.d("Claim Service", "The add Claim response : ${respStr}")
            }
            ctx.refreshStatusTrue()
            ctx.refreshScreen(claimList[currentIndx])
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            ctx.refreshStatusFalse()
            ctx.refreshScreen(claimList[currentIndx])
        }
    }

    fun addClaim(cObj : Claim) {
        val client = AsyncHttpClient()
        val requestUrl = "http://10.0.2.2:8010/ClaimService/add"
        // 1. Convert the cObj into JSON string
        val cJsonString= Gson().toJson(cObj)
        // 2. Send the post request
        val entity = StringEntity(cJsonString)
        // cxt is an Android Application Context object
        client.post(ctx, requestUrl, entity,"application/json", AddServiceRespHandler())
    }
    fun getAll()  {
        val client = AsyncHttpClient()
        val requestUrl = "http://10.0.2.2:8010/ClaimService/getAll"
        Log.d("Claim Service", "About Sending the HTTP Request. ")
        client.get(requestUrl, GetAllServiceRespHandler())
    }
}