import Link from 'next/link';

export default function HomePage() {
  const quickLinks = [
    { title: 'Create Title', href: '/dashboard/titles/new', description: 'Launch a new movie or series' },
    { title: 'Upload Asset', href: '/dashboard/assets', description: 'Manage HLS/DASH renditions and images' },
    { title: 'Schedule Window', href: '/dashboard/schedules', description: 'Control territory and time availability' }
  ];

  return (
    <div className="space-y-8">
      <div>
        <h2 className="text-3xl font-semibold">Welcome back 👋</h2>
        <p className="text-slate-400">Use the quick links below to jump into common workflows.</p>
      </div>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {quickLinks.map((link) => (
          <Link key={link.title} href={link.href} className="rounded-xl border border-slate-800 bg-slate-900/60 p-6 hover:border-primary transition">
            <h3 className="text-xl font-semibold mb-2">{link.title}</h3>
            <p className="text-slate-400 text-sm">{link.description}</p>
          </Link>
        ))}
      </div>
    </div>
  );
}
