package com.nnk.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {
    private final BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList findById(Integer id) {
        return bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid BidList ID: " + id));
    }

    public BidList save(BidList BidList) {
        return bidListRepository.save(BidList);
    }

    public BidList update(Integer id, BidList BidList) {
        BidList.setBidListId(id);
        return bidListRepository.save(BidList);
    }

    public void deleteById(Integer id) {
        bidListRepository.deleteById(id);
    }
}
