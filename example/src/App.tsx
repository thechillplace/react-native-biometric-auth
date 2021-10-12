import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import {
  hasBiometricCapability,
  authenticate,
} from 'react-native-biometric-auth';

export default function App() {
  const [result, setResult] = React.useState<string | undefined>();

  React.useEffect(() => {
    _checkHasBiometricCapability();
  }, []);

  const _checkHasBiometricCapability = async () => {
    try {
      const r = await hasBiometricCapability();
      setResult(`${r}`);
      if (r) {
        await authenticate({
          title: 'Biometric Authentication',
          subtitle: 'subtitle',
          description: 'description',
          allowDeviceCredential: true,
        });
      }
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
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
