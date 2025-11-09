import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, FlatList, TouchableOpacity, useTVEventHandler } from 'react-native';

const mockRows = [
  { id: 'trending', title: 'Trending', items: Array.from({ length: 10 }).map((_, i) => ({ id: `trend-${i}`, name: `Trending ${i + 1}` })) },
  { id: 'continue', title: 'Continue Watching', items: Array.from({ length: 5 }).map((_, i) => ({ id: `continue-${i}`, name: `Continue ${i + 1}` })) }
];

const App = () => {
  const [focusedRow, setFocusedRow] = useState(0);
  const [focusedIndex, setFocusedIndex] = useState(0);

  useTVEventHandler((evt) => {
    if (evt && evt.eventType === 'right' && focusedIndex < mockRows[focusedRow].items.length - 1) {
      setFocusedIndex((prev) => prev + 1);
    }
    if (evt && evt.eventType === 'left' && focusedIndex > 0) {
      setFocusedIndex((prev) => prev - 1);
    }
    if (evt && evt.eventType === 'down' && focusedRow < mockRows.length - 1) {
      setFocusedRow((prev) => prev + 1);
      setFocusedIndex(0);
    }
    if (evt && evt.eventType === 'up' && focusedRow > 0) {
      setFocusedRow((prev) => prev - 1);
      setFocusedIndex(0);
    }
  });

  useEffect(() => {
    setFocusedIndex(0);
  }, [focusedRow]);

  return (
    <View style={styles.container}>
      <Text style={styles.header}>StreamHub TV</Text>
      <FlatList
        data={mockRows}
        keyExtractor={(row) => row.id}
        renderItem={({ item, index: rowIndex }) => (
          <View style={styles.row}>
            <Text style={styles.rowTitle}>{item.title}</Text>
            <View style={styles.rowItems}>
              {item.items.map((tile, tileIndex) => {
                const focused = rowIndex === focusedRow && tileIndex === focusedIndex;
                return (
                  <TouchableOpacity
                    key={tile.id}
                    style={[styles.tile, focused && styles.tileFocused]}
                    activeOpacity={0.8}
                  >
                    <Text style={styles.tileText}>{tile.name}</Text>
                  </TouchableOpacity>
                );
              })}
            </View>
          </View>
        )}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#020617',
    padding: 48
  },
  header: {
    color: '#f8fafc',
    fontSize: 42,
    fontWeight: '700',
    marginBottom: 32
  },
  row: {
    marginBottom: 36
  },
  rowTitle: {
    color: '#cbd5f5',
    fontSize: 24,
    marginBottom: 16
  },
  rowItems: {
    flexDirection: 'row'
  },
  tile: {
    width: 260,
    height: 150,
    backgroundColor: '#1f2937',
    marginRight: 24,
    borderRadius: 16,
    justifyContent: 'center',
    alignItems: 'center'
  },
  tileFocused: {
    borderWidth: 4,
    borderColor: '#38bdf8',
    transform: [{ scale: 1.05 }]
  },
  tileText: {
    color: '#f8fafc',
    fontSize: 18
  }
});

export default App;
