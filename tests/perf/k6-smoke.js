import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 10,
  duration: '30s'
};

const BASE_URL = __ENV.BASE_URL ?? 'http://localhost:8080';

export default function () {
  const res = http.get(`${BASE_URL}/catalog/trending`);
  check(res, {
    'status is 200': (r) => r.status === 200,
    'payload is json': (r) => r.headers['Content-Type'] && r.headers['Content-Type'].includes('application/json')
  });
  sleep(1);
}
