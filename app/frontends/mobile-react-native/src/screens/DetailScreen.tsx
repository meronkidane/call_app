import React from 'react';
import { View, Text, StyleSheet, Button } from 'react-native';
import { useQuery } from 'react-query';
import { fetchTitle } from '../api/client';

const DetailScreen: React.FC<any> = ({ route }) => {
  const { id } = route.params;
  const { data, isLoading, error } = useQuery(['title', id], () => fetchTitle(id));
  const title = data?.data;

  return (
    <View style={styles.container}>
      {isLoading && <Text style={styles.text}>Loading...</Text>}
      {error && <Text style={styles.text}>Unable to load title.</Text>}
      {title && (
        <View>
          <Text style={styles.title}>{title.name}</Text>
          <Text style={styles.text}>{title.synopsis}</Text>
          <Text style={styles.meta}>{title.year} • {title.type}</Text>
          <Button title="Play" onPress={() => console.log('Play pressed')} />
        </View>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#0f172a',
    padding: 24
  },
  title: {
    color: '#f8fafc',
    fontSize: 28,
    fontWeight: '700',
    marginBottom: 8
  },
  text: {
    color: '#e2e8f0',
    marginBottom: 12
  },
  meta: {
    color: '#94a3b8',
    marginBottom: 16
  }
});

export default DetailScreen;
