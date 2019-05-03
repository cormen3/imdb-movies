package hossein.gheisary.data.remote.exception

import android.content.Context
import com.google.gson.Gson
import hossein.gheisary.data.R
import okhttp3.ResponseBody
import retrofit2.HttpException
import org.json.JSONException
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class ExceptionHandler @Inject constructor(var context: Context, var standardHttpErrorHandler: StandardHttpErrorHandler) {
    fun handleError(error: Throwable) : NetworkException {
        when (error) {
            is HttpException -> {
                val errorBody = (error.response().errorBody()as ResponseBody).string()

                if(errorBody.isNotEmpty() && isJSONValid(errorBody)){
                    return Gson().fromJson(errorBody, NetworkException::class.java)
                }else {
                    return standardHttpErrorHandler.handleError(error.code())
                }
            }
            is NetworkException ->
                if(isJSONValid(error.message)){
                    var data = Gson().fromJson(error.message, NetworkException::class.java)
                    return  data
                }else {
                    if(error.message.isNotEmpty()){
                        return NetworkException(error.message+ context.getString(R.string.error_code) + error.code)
                    }else {
                        return NetworkException(context.getString(R.string.server_error)+ context.getString(R.string.error_code) + error.code)
                    }
                }
            else ->{
                return NetworkException(context.getString(R.string.server_error))
            }
        }
    }

    private fun isJSONValid(test: String): Boolean {
        try {
            JSONObject(test)
        } catch (ex: JSONException) {
            try {
                JSONArray(test)
            } catch (ex1: JSONException) {
                return false
            }
        }
        return true
    }
}
