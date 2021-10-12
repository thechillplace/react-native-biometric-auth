import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-biometric-authenticate' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const BiometricAuth = NativeModules.BiometricAuth
  ? NativeModules.BiometricAuth
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function hasBiometricCapability(): Promise<any> {
  return BiometricAuth.hasBiometricCapability();
}

export function authenticate(option: object): Promise<any> {
  return BiometricAuth.authenticate(option);
}
