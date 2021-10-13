package com.reactnativebiometricauth

import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import com.facebook.react.bridge.*
import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.UiThreadUtil


class BiometricAuthModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext),
    BiometricAuthListener {

    companion object {
        lateinit var currentPromise: Promise;
    }

    override fun getName(): String {
      return "BiometricAuth"
    }

    // Check has biometric function
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    fun hasBiometricCapability(promise: Promise) {
        when (val result = BiometricUtil.hasBiometricCapability(reactApplicationContext)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                promise.resolve(true)
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                promise.reject(result.toString(), "BIOMETRIC_ERROR_HW_UNAVAILABLE")
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                promise.reject(result.toString(), "BIOMETRIC_ERROR_NONE_ENROLLED")
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                promise.reject(result.toString(), "BIOMETRIC_ERROR_NO_HARDWARE")
            }
        }
    }

    // authenticate biometric function
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    fun authenticate(option: ReadableMap, promise: Promise) {
        UiThreadUtil.runOnUiThread( Runnable() {
            currentPromise = promise
            BiometricUtil.showBiometricPrompt(
                title = option.getString("title") ?: "Biometric Authentication",
                subtitle = option.getString("subtitle") ?: "Enter biometric credentials to proceed.",
                description = option.getString("description") ?: "Input your Fingerprint or FaceID to ensure it's you!",
                activity = currentActivity as AppCompatActivity,
                listener = this,
                cryptoObject = null,
                allowDeviceCredential = option.getBoolean("allowDeviceCredential") ?: false
            )
        })
    }

    override fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult) {
        currentPromise.resolve(true)
    }

    override fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String) {
        currentPromise.reject(errorCode.toString(), errorMessage)
    }

}
