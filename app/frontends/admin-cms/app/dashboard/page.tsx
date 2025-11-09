import { Suspense } from 'react';
import TitlesTable from '../../components/TitlesTable';

export default function DashboardPage() {
  return (
    <div className="space-y-8">
      <div className="flex items-center justify-between">
        <h1 className="text-2xl font-semibold">Catalog Overview</h1>
        <a className="rounded-md bg-primary/20 px-4 py-2 text-primary" href="/dashboard/titles/new">Create Title</a>
      </div>
      <Suspense fallback={<div>Loading catalog…</div>}>
        <TitlesTable />
      </Suspense>
    </div>
  );
}
