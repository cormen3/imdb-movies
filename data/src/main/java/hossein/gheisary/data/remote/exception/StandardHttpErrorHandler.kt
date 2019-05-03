package hossein.gheisary.data.remote.exception

import android.content.Context
import com.global.data.network.exception.NetworkException
import hossein.gheisary.data.R
import javax.inject.Inject

class StandardHttpErrorHandler @Inject constructor(var context: Context) {
    fun handleError(errorCode: Int) : NetworkException {
        when (errorCode) {
            400  -> return NetworkException("bad request", 400)
            403 ->  return NetworkException("Forbidden", 403)
            404 ->  return NetworkException("Not Found", 404)
            408 ->  return NetworkException("Request Time Out", 404)
            410 ->  return NetworkException("Gone", 410)
            500 ->  return NetworkException("Internal Server Error", 500)
            502 ->  return NetworkException("Bad Gateway", 502)
            504 ->  return NetworkException("Gateway Time Out", 504)
            else -> return NetworkException(context.getString(R.string.server_error))
        }
    }
}
