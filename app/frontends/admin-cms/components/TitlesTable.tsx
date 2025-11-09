'use client';

import { useQuery } from '@tanstack/react-query';
import axios from 'axios';

const API_BASE = process.env.NEXT_PUBLIC_API_BASE ?? 'http://localhost:8082';

type Title = {
  id: string;
  name: string;
  type: string;
  year: number;
  premium: boolean;
};

async function fetchTitles() {
  const { data } = await axios.post<{ data: { items: Title[] } }>(`${API_BASE}/catalog/search`, { query: '', size: 12, page: 0 });
  return data.data.items;
}

export default function TitlesTable() {
  const { data, isLoading, error } = useQuery({ queryKey: ['titles'], queryFn: fetchTitles });

  if (isLoading) {
    return <div className="text-slate-400">Loading titles…</div>;
  }

  if (error) {
    return <div className="text-red-400">Failed to load titles.</div>;
  }

  return (
    <div className="overflow-hidden rounded-lg border border-slate-800">
      <table className="min-w-full divide-y divide-slate-800">
        <thead className="bg-slate-900/80">
          <tr>
            <th className="px-4 py-3 text-left text-xs font-medium uppercase tracking-wider text-slate-400">Name</th>
            <th className="px-4 py-3 text-left text-xs font-medium uppercase tracking-wider text-slate-400">Type</th>
            <th className="px-4 py-3 text-left text-xs font-medium uppercase tracking-wider text-slate-400">Year</th>
            <th className="px-4 py-3 text-left text-xs font-medium uppercase tracking-wider text-slate-400">Premium</th>
          </tr>
        </thead>
        <tbody className="divide-y divide-slate-800 bg-slate-900/40">
          {data?.map((title) => (
            <tr key={title.id}>
              <td className="px-4 py-3">{title.name}</td>
              <td className="px-4 py-3 capitalize text-slate-400">{title.type.toLowerCase()}</td>
              <td className="px-4 py-3 text-slate-400">{title.year}</td>
              <td className="px-4 py-3">
                <span className={`rounded-full px-3 py-1 text-xs ${title.premium ? 'bg-amber-500/20 text-amber-300' : 'bg-slate-700 text-slate-300'}`}>
                  {title.premium ? 'Premium' : 'Included'}
                </span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
