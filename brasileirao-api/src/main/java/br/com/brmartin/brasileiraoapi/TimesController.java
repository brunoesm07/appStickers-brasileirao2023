package br.com.brmartin.brasileiraoapi;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class TimesController {
    
    @Autowired
    private TimesRepository repositorio;

    @GetMapping("/times")
    public List<Time> obterTimes() {
        List<Time> times = repositorio.findByOrderByRanking();
        return times;
    }

    @PostMapping("/times")
    public ResponseEntity<Time> cadastrarLinguagem(@RequestBody Time time) {
        Time timeSalvo = repositorio.save(time);
        return new ResponseEntity<>(timeSalvo, HttpStatus.CREATED);
    } 

    @GetMapping("/times/{id}")
    public Time obterLinguagemPorId(@PathVariable String id) {
        return repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

   @PutMapping("/times/{id}")
   public Time atualizarTime(@PathVariable String id, @RequestBody Time time) {
        if(!repositorio.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } 
        time.setId(id);
        Time timeSalvo = repositorio.save(time);
        return timeSalvo;
   }

   @DeleteMapping("/times/{id}")
   public void excluirTime(@PathVariable String id) {
        repositorio.deleteById(id);
   }
}