package br.com.brmartin.brasileiraoapi;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimesRepository extends MongoRepository<Time, String> {
    List<Time> findByOrderByRanking();
}