import {environment} from "environments/environment.prod";
import axios from "axios";

export async function getTEValue(trading_equipment) {
    var te_value = 0;
    var changed_date = "";
    if (trading_equipment.toUpperCase() === "EUR" | trading_equipment.toUpperCase() === "GBP" | trading_equipment.toUpperCase() === "TRY") {
        await getCurrency().then(res => {
            te_value = 1.0 / res[trading_equipment.toUpperCase()];
            changed_date = res.changed_date
        })
    } else if (trading_equipment.toUpperCase() === "XAG" | trading_equipment.toUpperCase() === "XAU") {
        await getMetalCurrency().then(res => {
            te_value = res[trading_equipment.toUpperCase()];
            changed_date = res.changed_date
        })
    } else if (trading_equipment.toUpperCase() === "BTC" | trading_equipment.toUpperCase() === "ETH" | trading_equipment.toUpperCase() === "LTC") {
        await getCryptoCurrency().then(res => {
            te_value = res[trading_equipment.toUpperCase()];
            changed_date = res.changed_date
        })
    } else if (trading_equipment.toUpperCase() === "GOOGL" | trading_equipment.toUpperCase() === "AAPL" | trading_equipment.toUpperCase() === "GM") {
        await getStock().then(res => {
            te_value = res[trading_equipment.toUpperCase()];
            changed_date = res.changed_date
        })
    } else if (trading_equipment.toUpperCase() === "SPY" | trading_equipment.toUpperCase() === "IVV" | trading_equipment.toUpperCase() === "VTI") {
        await getETFs().then(res => {
            te_value = res[trading_equipment.toUpperCase()];
            changed_date = res.changed_date
        })
    }
    return {te_value: te_value, changed_date: changed_date}
}


export function getCurrency() {
    return axios
        .get(`${environment.api_url}equipment/currency/`)
        .then(res => (res.status === 200 ? res.data : null));
}

export function getMetalCurrency() {
    return axios
        .get(`${environment.api_url}equipment/metalcurrency/`)
        .then(res => (res.status === 200 ? res.data : null));
}


export function getCryptoCurrency() {
    return axios
        .get(`${environment.api_url}equipment/cryptocurrency/`)
        .then(res => (res.status === 200 ? res.data : null));
}

export function getStock() {
    return axios
        .get(`${environment.api_url}equipment/stock/`)
        .then(res => (res.status === 200 ? res.data : null));
}

export function getTradeIndices() {
    return axios
        .get(`${environment.api_url}equipment/traceindices/`)
        .then(res => (res.status === 200 ? res.data : null));
}

export function getETFs() {
    return axios
        .get(`${environment.api_url}equipment/etfs/`)
        .then(res => (res.status === 200 ? res.data : null));
}

export function getBonds() {
    return axios
        .get(`${environment.api_url}equipment/bonds/`)
        .then(res => (res.status === 200 ? res.data: null));
}

export function getTradeIndicesGainer() {
    return axios
        .get(`${environment.api_url}equipment/traceindicesgainers/`)
        .then(res => (res.status === 200 ? res.data : null));
}


export async function getTEHistory(trading_equipment) {
    var x = [];
    var y = [];
    if (trading_equipment.toUpperCase() === "EUR" | trading_equipment.toUpperCase() === "GBP" | trading_equipment.toUpperCase() === "TRY") {
        await getCurrencyHistory().then(res => {
            res.results.forEach(
                function (element) {
                    x.push(element[trading_equipment.toUpperCase()]);
                    y.push(element.changed_time);
                })
        })
    } else if (trading_equipment.toUpperCase() === "XAG" | trading_equipment.toUpperCase() === "XAU") {
        await getMetalCurrencyHistory().then(res => {
            res.results.forEach(
                function (element) {
                    x.push(element[trading_equipment.toUpperCase()]);
                    y.push(element.changed_time);
                })
        })
    } else if (trading_equipment.toUpperCase() === "BTC" | trading_equipment.toUpperCase() === "ETH" | trading_equipment.toUpperCase() === "LTC") {
        await getCryptoCurrencyHistory().then(res => {
            res.results.forEach(
                function (element) {
                    x.push(element[trading_equipment.toUpperCase()]);
                    y.push(element.changed_time);
                })
        })
    } else if (trading_equipment.toUpperCase() === "GOOGL" | trading_equipment.toUpperCase() === "AAPL" | trading_equipment.toUpperCase() === "GM") {
        await getStockHistory().then(res => {
            res.results.forEach(
                function (element) {
                    x.push(element[trading_equipment.toUpperCase()]);
                    y.push(element.changed_time);
                })
        })
    } else if (trading_equipment.toUpperCase() === "SPY" | trading_equipment.toUpperCase() === "IVV" | trading_equipment.toUpperCase() === "VTI") {
        await getETFHistory().then(res => {
            res.results.forEach(
                function (element) {
                    x.push(element[trading_equipment.toUpperCase()]);
                    y.push(element.changed_time);
                })
        })
    }
    return {
        values: x,
        dates: y
    };
}


export function getCurrencyHistory() {
    return axios
        .get(`${environment.api_url}equipment/currencyList/`)
        .then(res => (res.status === 200 ? res.data : null));
}

export function getMetalCurrencyHistory() {
    return axios
        .get(`${environment.api_url}equipment/metalcurrencyList/`)
        .then(res => (res.status === 200 ? res.data : null));
}

export function getCryptoCurrencyHistory() {
    return axios
        .get(`${environment.api_url}equipment/cryptocurrencyList/`)
        .then(res => (res.status === 200 ? res.data : null));
}

export function getStockHistory() {
    return axios
        .get(`${environment.api_url}equipment/stockcurrencyList/`)
        .then(res => (res.status === 200 ? res.data : null));
}

export function getETFHistory() {
    return axios
        .get(`${environment.api_url}equipment/etfList/`)
        .then(res => (res.status === 200 ? res.data : null));
}