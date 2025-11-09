import React from 'react';
import { FlatList, TouchableOpacity, View, Text, StyleSheet } from 'react-native';

type ContinueItem = {
  contentId: string;
  positionSec: number;
  durationSec: number;
  title: string;
  artworkUrl?: string;
};

type Props = {
  items: ContinueItem[];
  onSelect: (item: ContinueItem) => void;
};

const ContinueWatchingRail: React.FC<Props> = ({ items, onSelect }) => {
  if (!items.length) {
    return (
      <View style={styles.placeholder}>
        <Text style={styles.placeholderText}>Nothing to resume yet.</Text>
      </View>
    );
  }

  return (
    <FlatList
      horizontal
      showsHorizontalScrollIndicator={false}
      data={items}
      keyExtractor={(item) => item.contentId}
      renderItem={({ item }) => (
        <TouchableOpacity style={styles.item} onPress={() => onSelect(item)}>
          <View style={styles.progressBar}>
            <View style={[styles.progress, { width: `${Math.min(100, (item.positionSec / Math.max(1, item.durationSec)) * 100)}%` }]} />
          </View>
          <Text style={styles.title} numberOfLines={2}>{item.title}</Text>
        </TouchableOpacity>
      )}
    />
  );
};

const styles = StyleSheet.create({
  placeholder: {
    height: 100,
    justifyContent: 'center'
  },
  placeholderText: {
    color: '#94a3b8'
  },
  item: {
    width: 180,
    height: 140,
    backgroundColor: '#1e293b',
    borderRadius: 12,
    marginRight: 16,
    padding: 12,
    justifyContent: 'space-between'
  },
  progressBar: {
    height: 4,
    backgroundColor: '#334155',
    borderRadius: 2,
    overflow: 'hidden'
  },
  progress: {
    height: 4,
    backgroundColor: '#38bdf8'
  },
  title: {
    color: '#f8fafc',
    fontWeight: '600'
  }
});

export default ContinueWatchingRail;
