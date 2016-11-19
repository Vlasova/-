package ru.vlasova.exchangeRates.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс валюта
 */
public class Currency {

    private CurrenciesNames name;
    private float exchange;

    public Currency(CurrenciesNames name, String date) {
        this.name = name;
        HTMLParser parser = new HTMLParser(date);
        exchange = new BigDecimal(parser.getExchangeByName(name)).setScale(4, RoundingMode.HALF_UP).floatValue();
    }

    /**
     * Получить стоимость валюты
     * @return стоимость
     */
    public float getExchange() {
        return exchange;
    }

    /**
     * Получить буквенный код валюты
     * @return код
     */
    public CurrenciesNames getName() {
        return name;
    }

    /**
     * Получить русское название валюты
     * @return название
     */
    public String getRussianName() {
        return name.getRussianName();
    }

    /**
     * Узнать, увеличилась ли стоимость валюты с предыдущего дня
     * @return true если курс повысился
     *         false если курс понизился
     */
    public boolean isTodayHigher() {
        HTMLParser parser = new HTMLParser(new Day().getYesterdayDate());
        float yesterdayExchange = parser.getExchangeByName(name);
        return exchange > yesterdayExchange;
    }
}

