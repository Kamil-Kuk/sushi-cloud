package sia.sushicloud.contoller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.sushicloud.model.Sushi;
import sia.sushicloud.persistence.JpaSushiRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/design", produces = {"application/json", "text/xml"})
@CrossOrigin(origins = "*")
public class DesignSushiRestController {

    private JpaSushiRepository sushiRepository;

    @Autowired
    EntityLinks entityLinks;

    public DesignSushiRestController(JpaSushiRepository sushiRepository) {
        this.sushiRepository = sushiRepository;
    }

    @GetMapping("/recent")
    public Iterable<Sushi> recentSushi(){
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return sushiRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sushi> getById(@PathVariable("id") Long id){
        Optional<Sushi> optionalSushi = sushiRepository.findById(id);
        if(optionalSushi.isPresent()) {
            return new ResponseEntity<>(optionalSushi.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Sushi postSushi(@RequestBody Sushi sushi){
        return sushiRepository.save(sushi);
    }

    @PutMapping("{sushiId}")
    public Sushi updateSushi(@RequestBody Sushi sushi){
        return sushiRepository.save(sushi);
    }
}
