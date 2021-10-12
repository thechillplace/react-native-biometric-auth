package com.reactnativebiometricauth

import androidx.biometric.BiometricManager
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise

class BiometricAuthModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
      return "BiometricAuth"
    }

    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    fun multiply(a: Int, b: Int, promise: Promise) {

        promise.resolve(a * b)

    }

    // Check has biometric function
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    fun hasBiometricCapability(promise: Promise) {
        val result = BiometricUtil.hasBiometricCapability(reactApplicationContext)
        if (result == BiometricManager.BIOMETRIC_SUCCESS) {
            promise.resolve(true)
        } else if (result == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE) {
            promise.reject(result.toString(), "BIOMETRIC_ERROR_HW_UNAVAILABLE")
        } else if (result == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) {
            promise.reject(result.toString(), "BIOMETRIC_ERROR_NONE_ENROLLED")
        } else if (result == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE) {
            promise.reject(result.toString(), "BIOMETRIC_ERROR_NO_HARDWARE")
        }
    }



}
