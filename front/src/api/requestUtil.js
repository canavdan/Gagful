import axios from 'axios'
import { API_BASE_URL, ACCESS_TOKEN } from '../constants'

function checkStatus(response) {
  if (response.status <= 200 && response.status < 300) {
    return response
  }
}

function parseJson(response) {
  const { data } = response
  if (!data || data.success == 'false') {
    const error = new Error('Error for getting data from server')
    throw error
  }
  return data
}

export function request(options) {
  const headers = new Headers({
    'Content-Type': 'application/json',
  })

  if (localStorage.getItem(ACCESS_TOKEN)) {
    headers.append(
      'Authorization',
      `Bearer ${localStorage.getItem(ACCESS_TOKEN)}`,
    )
  }
  const defaults = { headers }
  options = { ...defaults, ...options }

  /* return fetch(options.url, options).then((response) => response.json().then((json) => {
    if (!response.ok) {
      return Promise.reject(json)
    }
    return json
  })) */

  return axios(options.url, options)
    .then(checkStatus)
    .then(parseJson)
    .then((data) => ({ data }))
    .catch((err) => ({ err }))
}


export function get(url) {
  return request({
    url: `${API_BASE_URL}${url}`,
    method: 'GET',
  })
}
export function post(url, data) {
  return request({
    url: `${API_BASE_URL}${url}`,
    method: 'POST',
    body: JSON.stringify(data),
  })
}

export function put(url, data) {
  return request({
    url: `${API_BASE_URL}${url}`,
    method: 'PUT',
    body: JSON.stringify(data),
  })
}

export function remove(url, data) {
  return request({
    url: `${API_BASE_URL}${url}`,
    method: 'DELETE',
    body: JSON.stringify(data),
  })
}
