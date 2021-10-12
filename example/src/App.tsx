import * as React from 'react';

import { StyleSheet, View, Text, Button } from 'react-native';
import {
  hasBiometricCapability,
  authenticate,
} from 'react-native-biometric-authenticate';

export default function App() {
  const [result, setResult] = React.useState<string | undefined>();

  const _checkHasBiometricCapability = async () => {
    try {
      const r = await hasBiometricCapability();
      if (r) {
        const res = await authenticate({
          title: 'Đăng nhập',
          subtitle: 'subtitle',
          description: 'description',
          allowDeviceCredential: false,
        });
        setResult(`${res}`);
      }
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
      <Button onPress={_checkHasBiometricCapability} title="Login" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
