# react-native-biometric-authenticate

a wrapper biometric authentication

We are developing...

## Installation

```sh
npm install react-native-biometric-authenticate
```

## Usage

```js
import { hasBiometricCapability, authenticate } from "react-native-biometric-authenticate";

// ...

const result = await hasBiometricCapability();

const res = await authenticate({
  title: 'Login',
  subtitle: 'Login by Biometric feature',
  description: 'description',
  allowDeviceCredential: true,
});
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
