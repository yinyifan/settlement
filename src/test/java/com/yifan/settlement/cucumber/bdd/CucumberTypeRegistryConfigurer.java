package com.yifan.settlement.cucumber.bdd;

import static java.util.Locale.ENGLISH;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yifan.settlement.dto.request.SettlementCreationRequest;
import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.*;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class CucumberTypeRegistryConfigurer implements TypeRegistryConfigurer {

    private ObjectMapper mapper;

    public CucumberTypeRegistryConfigurer() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
    }

    @Override
    public Locale locale() {
        return ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineDataTableType(new DataTableType(SettlementCreationRequest.class, new TableEntryTransformer<SettlementCreationRequest>() {
            @Override
            public SettlementCreationRequest transform(Map<String, String> entry) {
                return new SettlementCreationRequest(entry.get("tradeId"),entry.get("ssiCode"),new BigDecimal(entry.get("amount")),entry.get("currency"),entry.get("valueDate"));
            }
        }));
    }

}