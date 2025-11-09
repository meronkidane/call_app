import React from 'react';
import { View, Text, FlatList, StyleSheet, TouchableOpacity } from 'react-native';
import { useQuery } from 'react-query';
import { fetchHomeRails } from '../api/client';
import ContinueWatchingRail from '../components/ContinueWatchingRail';

const PROFILE_ID = 'mock-profile-id';

const HomeScreen: React.FC<any> = ({ navigation }) => {
  const { data, isLoading, error } = useQuery(['home', PROFILE_ID], () => fetchHomeRails(PROFILE_ID));

  if (isLoading) {
    return (
      <View style={styles.container}>
        <Text style={styles.subtitle}>Loading...</Text>
      </View>
    );
  }

  if (error || !data) {
    return (
      <View style={styles.container}>
        <Text style={styles.subtitle}>Unable to fetch catalog data.</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Continue Watching</Text>
      <ContinueWatchingRail items={data.continueWatching} onSelect={(item) => navigation.navigate('Detail', { id: item.contentId })} />

      <Text style={styles.title}>Trending</Text>
      <FlatList
        data={data.trending}
        keyExtractor={(item) => item.id}
        horizontal
        renderItem={({ item }) => (
          <TouchableOpacity style={styles.card} onPress={() => navigation.navigate('Detail', { id: item.id })}>
            <Text style={styles.cardTitle}>{item.name}</Text>
          </TouchableOpacity>
        )}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#0f172a',
    paddingTop: 64,
    paddingHorizontal: 16
  },
  title: {
    color: '#f8fafc',
    fontSize: 22,
    fontWeight: '700',
    marginVertical: 16
  },
  subtitle: {
    color: '#f1f5f9'
  },
  card: {
    width: 200,
    height: 120,
    backgroundColor: '#1e293b',
    borderRadius: 12,
    marginRight: 12,
    justifyContent: 'center',
    alignItems: 'center'
  },
  cardTitle: {
    color: '#f8fafc',
    textAlign: 'center'
  }
});

export default HomeScreen;
