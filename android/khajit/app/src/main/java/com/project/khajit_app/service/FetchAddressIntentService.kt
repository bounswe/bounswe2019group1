package com.project.khajit_app.service

import android.app.IntentService
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import java.io.IOException
import java.util.*

class FetchAddressIntentService : IntentService("FetchAddressIntentService") {

    private var receiver: ResultReceiver? = null


    override fun onHandleIntent(intent: Intent?) {
        val geocoder = Geocoder(this, Locale.getDefault())

        intent ?: return

        var errorMessage = ""

        // Get the location passed to this service through an extra.
        val location = intent.getParcelableExtra<Location?>(Constants.LOCATION_DATA_EXTRA)
        receiver = intent.getParcelableExtra(Constants.RECEIVER)

        var addresses: List<Address> = emptyList()

        try {
            addresses = geocoder.getFromLocation(
                location!!.latitude,
                location.longitude,
                // get just a single address.
                1)
        } catch (ioException: IOException) {
            // Catch network or other I/O problems.
            errorMessage = "Service is not available."
            Log.e(TAG, errorMessage, ioException)
        } catch (illegalArgumentException: IllegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = "Invalid latitude or longitude values."
            Log.e(
                TAG, "$errorMessage. Latitude = $location.latitude , " +
                        "Longitude =  $location.longitude", illegalArgumentException)
        }


        // Handle case where no address was found.
        if (addresses.isEmpty()) {
            if (errorMessage.isEmpty()) {
                errorMessage = "No addresses found matching your location."
                Log.e(TAG, errorMessage)
            }
            deliverResultToReceiver(Constants.FAILURE_RESULT, errorMessage)
        } else {
            // Address is found.
            val address = addresses[0]
            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            val addressFragments = with(address) {
                (0..maxAddressLineIndex).map { getAddressLine(it) }
            }
            Log.i(TAG, "Address is found.")
            deliverResultToReceiver(
                Constants.SUCCESS_RESULT,
                addressFragments.joinToString(separator = " "))
        }

    }

    private fun deliverResultToReceiver(resultCode: Int, message: String) {
        val bundle = Bundle().apply { putString(Constants.RESULT_DATA_KEY, message) }
        receiver?.send(resultCode, bundle)
    }


    object Constants {
        const val SUCCESS_RESULT = 0
        const val FAILURE_RESULT = 1
        const val PACKAGE_NAME = "com.project.khajit_app.service"
        const val RECEIVER = "$PACKAGE_NAME.RECEIVER"
        const val RESULT_DATA_KEY = "$PACKAGE_NAME.RESULT_DATA_KEY"
        const val LOCATION_DATA_EXTRA = "$PACKAGE_NAME.LOCATION_DATA_EXTRA"

    }

    companion object {
        const val TAG = "GoogleFetchAddressIntentService"
    }
}