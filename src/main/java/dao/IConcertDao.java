package dao;

import java.util.List;

import metier.entities.Concert;

public interface IConcertDao {
    public Concert save(Concert c);
    public List<Concert> concertsParMC(String mc);
    public Concert getConcert(Long id);
    public Concert updateConcert(Concert c);
    public void deleteConcert(Long id);
}
