package sia.sushicloud.contoller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.sushicloud.model.Sushi;
import sia.sushicloud.model.utils.SushiModel;
import sia.sushicloud.model.utils.SushiModelAssembler;
import sia.sushicloud.persistence.JpaSushiRepository;


import java.util.List;
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
    public CollectionModel<SushiModel> recentSushi(){
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());

        List<Sushi> sushiList = sushiRepository.findAll(page).getContent();
        CollectionModel<SushiModel> recentModels = new SushiModelAssembler().toCollectionModel(sushiList);

//        CollectionModel<EntityModel<Sushi>> model = CollectionModel.wrap(sushiList);
//        model.add(Link.of("http://localhost:8080/design/recent", "recents"));
        recentModels.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DesignSushiRestController.class).recentSushi()).withRel("recents"));
        return recentModels;
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
