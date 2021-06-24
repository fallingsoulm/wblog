import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'

const RefreshTokenKey = 'Admin-Refresh-Token'

const ExpiresInKey = 'Admin-Expires-In'

export function getToken() {
  return localStorage.getItem(TokenKey);
  // return Cookies.get(TokenKey)
}

export function setToken(token) {
  return localStorage.setItem(TokenKey, token)
  // return Cookies.set(TokenKey, token)
}

export function removeToken() {
  localStorage.removeItem(TokenKey)
  // return Cookies.remove(TokenKey)
}

export function getRefreshToken() {
  // return Cookies.get(RefreshTokenKey) || ``
  return localStorage.getItem(RefreshTokenKey) || ``
}

export function setRefreshToken(token) {
  return localStorage.setItem(RefreshTokenKey, token)
  // return Cookies.set(RefreshTokenKey, token)
}

export function removeRefreshToken() {
  localStorage.removeItem(RefreshTokenKey)
  // return Cookies.remove(RefreshTokenKey)
}

export function getExpiresIn() {
  return localStorage.getItem(ExpiresInKey) || -1
  // return Cookies.get(ExpiresInKey) || -1
}

export function setExpiresIn(time) {
  return localStorage.setItem(ExpiresInKey, time)
  // return Cookies.set(ExpiresInKey, time)
}

export function removeExpiresIn() {
  return localStorage.removeItem(ExpiresInKey)
  // return Cookies.remove(ExpiresInKey)
}
