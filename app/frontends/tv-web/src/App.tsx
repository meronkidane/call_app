import React, { useState } from 'react';
import { useQuery } from 'react-query';

const PROFILE_ID = 'mock-profile-id';
const API_BASE = import.meta.env.VITE_API_BASE ?? 'http://localhost:8080';

async function fetchContinue() {
  const res = await fetch(`${API_BASE}/catalog/continue/${PROFILE_ID}`);
  if (!res.ok) {
    throw new Error('Failed to fetch continue watching');
  }
  return res.json();
}

function App() {
  const [focused, setFocused] = useState<string | null>(null);
  const { data, isLoading, error } = useQuery(['continue'], fetchContinue);

  return (
    <div className="app">
      <header className="header">StreamHub TV Web</header>
      <section>
        <h2>Continue Watching</h2>
        <div className="rail">
          {isLoading && <div className="card">Loading...</div>}
          {error && <div className="card error">Error loading data</div>}
          {data && data.map((item: any) => (
            <button
              key={item.contentId}
              className={`card ${focused === item.contentId ? 'focused' : ''}`}
              onFocus={() => setFocused(item.contentId)}
            >
              <div className="title">{item.title}</div>
              <div className="progress">
                <div
                  className="progress-inner"
                  style={{ width: `${Math.min(100, (item.positionSec / Math.max(1, item.durationSec)) * 100)}%` }}
                />
              </div>
            </button>
          ))}
        </div>
      </section>
    </div>
  );
}

export default App;
