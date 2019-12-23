import {environment} from "environments/environment.prod";
import axios from "axios";

export async function getTEValue(trading_equipment) {
    var te_value = 0;
    var changed_date = "";
    if (trading_equipment === "EUR" | trading_equipment === "GBP" | trading_equipment === "TRY") {
        await getCurrency().then(res => {
            te_value = res[trading_equipment];
            changed_date = res.changed_date
        })
    } else if (trading_equipment === "XAG" | trading_equipment === "XAU") {
        await getMetalCurrency().then(res => {
            te_value = res[trading_equipment];
            changed_date = res.changed_date
        })
    } else if (trading_equipment === "BTC" | trading_equipment === "ETH" | trading_equipment === "LTC") {
        await getCryptoCurrency().then(res => {
            te_value = res[trading_equipment];
            changed_date = res.changed_date
        })
    } else if (trading_equipment === "GOOGL" | trading_equipment === "AAPL" | trading_equipment === "GM") {
        await getStock().then(res => {
            te_value = res[trading_equipment];
            changed_date = res.changed_date
        })
    } else if (trading_equipment === "SPY" | trading_equipment === "IVV" | trading_equipment === "VTI") {
        await getETFs().then(res => {
            te_value = res[trading_equipment];
            changed_date = res.changed_date
        })
    }
}


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


export async function getTEHistory(trading_equipment) {
    var x = [];
    var y = [];
    if (trading_equipment === "EUR" | trading_equipment === "GBP" | trading_equipment === "TRY") {
        await getCurrencyHistory().then(res => {
            res.results.forEach(
                function (element) {
                    x.push(element[trading_equipment]);
                    y.push(element.changed_time);
                })
        })
    } else if (trading_equipment === "XAG" | trading_equipment === "XAU") {
        await getMetalCurrencyHistory().then(res => {
            res.results.forEach(
                function (element) {
                    x.push(element[trading_equipment]);
                    y.push(element.changed_time);
                })
        })
    } else if (trading_equipment === "BTC" | trading_equipment === "ETH" | trading_equipment === "LTC") {
        await getCryptoCurrencyHistory().then(res => {
            res.results.forEach(
                function (element) {
                    x.push(element[trading_equipment]);
                    y.push(element.changed_time);
                })
        })
    } else if (trading_equipment === "GOOGL" | trading_equipment === "AAPL" | trading_equipment === "GM") {
        await getStockHistory().then(res => {
            res.results.forEach(
                function (element) {
                    x.push(element[trading_equipment]);
                    y.push(element.changed_time);
                })
        })
    } else if (trading_equipment === "SPY" | trading_equipment === "IVV" | trading_equipment === "VTI") {
        await getETFHistory().then(res => {
            res.results.forEach(
                function (element) {
                    x.push(element[trading_equipment]);
                    y.push(element.changed_time);
                })
        })
    }
    return {
        values: x,
        times: y
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