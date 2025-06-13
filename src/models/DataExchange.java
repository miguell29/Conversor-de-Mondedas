package models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record DataExchange(String result,
                           @SerializedName("time_last_update_utc")
                           String lastUpdateUtc,
                           @SerializedName("base_code")
                           String baseCode,
                           @SerializedName("conversion_rates")
                           Map<String, Double> conversionRates,
                           @SerializedName("error-type")
                           String error) {
}