//
//  RNBiometricAuthBridge.m
//  BiometricAuthExample
//
//  Created by Duc Nguyen on 11/6/21.
//
#import <React/RCTBridgeModule.h>
#import <Foundation/Foundation.h>

@interface RCT_EXTERN_MODULE(BiometricAuth, NSObject)

RCT_EXTERN_METHOD(hasBiometricCapability:
                  (RCTPromiseResolveBlock) resolve
                  reject: (RCTPromiseRejectBlock) reject)

RCT_EXTERN_METHOD(authenticate:
                  (nullable NSDictionary) option
                  resolve: (RCTPromiseResolveBlock) resolve
                  reject: (RCTPromiseRejectBlock) reject)

@end
