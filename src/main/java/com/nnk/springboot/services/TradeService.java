package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    public Trade findById(Integer id) {
        return tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid CurvePoint ID: " + id));
    }

    public Trade save(Trade curvePoint) {
        return tradeRepository.save(curvePoint);
    }

    public Trade update(Integer id, Trade trade) {
        trade.setTradeId(id);
        return tradeRepository.save(trade);
    }

    public void deleteById(Integer id) {
        tradeRepository.deleteById(id);
    }

}
