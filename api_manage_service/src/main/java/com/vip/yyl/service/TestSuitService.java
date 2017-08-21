package com.vip.yyl.service;

import com.vip.yyl.domain.TestSuit;
import java.util.List;

/**
 * Service Interface for managing TestSuit.
 */
public interface TestSuitService {

    /**
     * Save a testSuit.
     *
     * @param testSuit the entity to save
     * @return the persisted entity
     */
    TestSuit save(TestSuit testSuit);

    /**
     *  Get all the testSuits.
     *  
     *  @return the list of entities
     */
    List<TestSuit> findAll();

    /**
     *  Get the "id" testSuit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TestSuit findOne(String id);

    /**
     *  Delete the "id" testSuit.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
