import './globals.css';
import type { Metadata } from 'next';
import Providers from './providers';

export const metadata: Metadata = {
  title: 'StreamHub Admin CMS',
  description: 'Manage catalog metadata, playlists, and schedules'
};

export default function RootLayout({
  children
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className="bg-slate-950 text-slate-100">
        <Providers>
          <div className="min-h-screen flex">
            <aside className="w-64 bg-slate-900 p-6 space-y-6">
              <h1 className="text-2xl font-bold">StreamHub CMS</h1>
              <nav className="space-y-4">
                <a className="block text-slate-300 hover:text-primary" href="/dashboard">Dashboard</a>
                <a className="block text-slate-300 hover:text-primary" href="/dashboard/titles">Titles</a>
                <a className="block text-slate-300 hover:text-primary" href="/dashboard/assets">Assets</a>
                <a className="block text-slate-300 hover:text-primary" href="/dashboard/schedules">Schedules</a>
                <a className="block text-slate-300 hover:text-primary" href="/dashboard/analytics">Analytics</a>
              </nav>
            </aside>
            <main className="flex-1 p-10">
              {children}
            </main>
          </div>
        </Providers>
      </body>
    </html>
  );
}
