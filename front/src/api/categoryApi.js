import { getData, postData } from './requestUtil'

export function getCategories() {
  return getData('/public/categories')
}

export function addCategory(data) {
  return postData('/public/categories/save', data)
}
