import { environment } from "environments/environment.prod";
import axios from "axios";

export function getCurrency() {
  return axios
    .get(`${environment.api_url}equipment/currency/`)
    .then(res => (res.status === 200 ? res.data.token : null));
}

export function getCryptoCurrency() {
  return axios
    .get(`${environment.api_url}equipment/cryptocurrency/`)
    .then(res => (res.status === 200 ? res.data.token : null));
}
export function getStock() {
  return axios
    .get(`${environment.api_url}equipment/stock/`)
    .then(res => (res.status === 200 ? res.data.token : null));
}
export function getTradeIndices() {
  return axios
    .get(`${environment.api_url}equipment/traceindices/`)
    .then(res => (res.status === 200 ? res.data.token : null));
}
export function getETFs() {
  return axios
    .get(`${environment.api_url}equipment/etfs/`)
    .then(res => (res.status === 200 ? res.data.token : null));
}
export function getBonds() {
  return axios
    .get(`${environment.api_url}equipment/bonds/`)
    .then(res => (res.status === 200 ? res.data.token : null));
}
export function getTradeIndicesGainer() {
  return axios
    .get(`${environment.api_url}equipment/traceindicesgainers/`)
    .then(res => (res.status === 200 ? res.data.token : null));
}
// Make the urls correct
export function getCurrencyHistory(){
  return axios
      .get(`${environment.api_url}equipment/currencyList/`)
      .then(res => (res.status === 200 ? res.data.token : null));
}

export function getCurrencyHistory(){
  return axios
      .get(`${environment.api_url}equipment/metalcurrencyList/`)
      .then(res => (res.status === 200 ? res.data.token : null));
}

export function getCryptoCurrencyHistory(){
  return axios
      .get(`${environment.api_url}equipment/cryptocurrencyList/`)
      .then(res => (res.status === 200 ? res.data.token : null));
}

export function getStockHistory(){
  return axios
      .get(`${environment.api_url}equipment/stockcurrencyList/`)
      .then(res => (res.status === 200 ? res.data.token : null));
}

export function getETFHistory(){
  return axios
      .get(`${environment.api_url}equipment/etfList/`)
      .then(res => (res.status === 200 ? res.data.token : null));
}

export function getTraceHistory(){
  return axios
      .get(`${environment.api_url}equipment/traceList/`)
      .then(res => (res.status === 200 ? res.data.token : null));
}