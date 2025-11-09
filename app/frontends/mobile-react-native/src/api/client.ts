const BASE_URL = process.env.EXPO_PUBLIC_API_URL ?? 'http://localhost:8080';

type HttpMethod = 'GET' | 'POST' | 'PUT' | 'DELETE';

async function request<T>(path: string, method: HttpMethod = 'GET', body?: unknown): Promise<T> {
  const response = await fetch(`${BASE_URL}${path}`, {
    method,
    headers: {
      'Content-Type': 'application/json'
    },
    body: body ? JSON.stringify(body) : undefined
  });

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Request failed: ${response.status} ${errorText}`);
  }

  return response.json() as Promise<T>;
}

export async function fetchHomeRails(profileId: string) {
  const [trending, continueWatching, recommendations] = await Promise.all([
    request<{ data: any[] }>('/catalog/trending'),
    request<any[]>(`/catalog/continue/${profileId}`),
    request<{ data: { items: string[] } }>(`/recs/home/${profileId}`)
  ]);

  return {
    trending: trending.data ?? [],
    continueWatching,
    recommendations: recommendations.data?.items ?? []
  };
}

export async function fetchTitle(id: string) {
  return request<{ data: any }>(`/catalog/title/${id}`);
}
