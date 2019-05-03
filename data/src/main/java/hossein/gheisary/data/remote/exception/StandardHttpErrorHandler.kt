package hossein.gheisary.data.remote.exception

import android.content.Context
import hossein.gheisary.data.R
import javax.inject.Inject

class StandardHttpErrorHandler @Inject constructor(var context: Context) {
    fun handleError(errorCode: Int) : NetworkException {
        when (errorCode) {
            400  -> return NetworkException(context.getString(R.string.bad_request), 400)
            403 ->  return NetworkException(context.getString(R.string.forbidden), 403)
            404 ->  return NetworkException(context.getString(R.string.not_found), 404)
            408 ->  return NetworkException(context.getString(R.string.time_out), 404)
            410 ->  return NetworkException(context.getString(R.string.gone), 410)
            500 ->  return NetworkException(context.getString(R.string.internal_server_error), 500)
            502 ->  return NetworkException(context.getString(R.string.bad_gateway), 502)
            504 ->  return NetworkException(context.getString(R.string.gateway_time_out), 504)
            else -> return NetworkException(context.getString(R.string.server_error))
        }
    }
}

