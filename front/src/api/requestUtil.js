import { API_BASE_URL, ACCESS_TOKEN } from '../constants';

const request = (options) => {
  const headers = new Headers({
    'Content-Type': 'application/json',
  });

  if (localStorage.getItem(ACCESS_TOKEN)) {
    headers.append(
      'Authorization',
      `Bearer ${localStorage.getItem(ACCESS_TOKEN)}`,
    );
  }
  const defaults = { headers };
  options = { ...defaults, ...options };
  return fetch(options.url, options).then((response) => response.json().then((json) => {
    if (!response.ok) {
      return Promise.reject(json);
    }
    return json;
  }));
};

export function getData(url) {
  return request({
    url: `${API_BASE_URL}${url}`,
    method: 'GET',
  });
}
export function postData(url, data) {
  return request({
    url: `${API_BASE_URL}${url}`,
    method: 'POST',
    body: JSON.stringify(data),
  });
}

export function putData(url, data) {
  return request({
    url: `${API_BASE_URL}${url}`,
    method: 'PUT',
    body: JSON.stringify(data),
  });
}

export function deleteData(url, data) {
  return request({
    url: `${API_BASE_URL}${url}`,
    method: 'DELETE',
    body: JSON.stringify(data),
  });
}
