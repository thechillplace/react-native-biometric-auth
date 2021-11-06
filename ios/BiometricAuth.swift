//
//  RNBiometricAuth.swift
//  BiometricAuthExample
//
//  Created by Duc Nguyen on 11/6/21.
//

import Foundation
import LocalAuthentication

@objc(BiometricAuth)
class BiometricAuth: NSObject {
  var context = LAContext()
  /**
    Check if device is ready to use biometric authentication
   */
  @objc
  func hasBiometricCapability(_ resolve: RCTPromiseResolveBlock, reject: RCTPromiseRejectBlock) {
    var error: NSError?
    if context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) {
      resolve(true)
    } else {
      resolve(false)
    }
  }

  /**
   Authenticate using biometric
   */
  @objc
  func authenticate(_ option: NSDictionary? = [:],
                    resolve: @escaping RCTPromiseResolveBlock,
                    reject: @escaping RCTPromiseRejectBlock) {
    let allowDeviceCredential = (option?["allowDeviceCredential"] as? NSNumber)?.boolValue ?? true
    let reason = (option?["description"] as? String) ?? "Authenticate"
    let authenticatePolicy = (allowDeviceCredential) ? LAPolicy.deviceOwnerAuthentication : LAPolicy.deviceOwnerAuthenticationWithBiometrics

    context.evaluatePolicy(authenticatePolicy, localizedReason: reason ) { success, error in

      // Authenticate successfully
      if success {
        resolve(true)
        return
      }

      // Authenticate failed
      let message = error?.localizedDescription ?? "Failed to authenticate"
      print(message)
      reject(message, message, error)
    }
  }
}
