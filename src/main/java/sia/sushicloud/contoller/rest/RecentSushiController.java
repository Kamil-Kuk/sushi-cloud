package sia.sushicloud.contoller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.sushicloud.model.Sushi;
import sia.sushicloud.model.utils.SushiModel;
import sia.sushicloud.model.utils.SushiModelAssembler;
import sia.sushicloud.persistence.JpaSushiRepository;

import java.util.List;

@RepositoryRestController
public class RecentSushiController {

    private JpaSushiRepository sushiRepository;

    @Autowired
    public RecentSushiController(JpaSushiRepository sushiRepository) {
        this.sushiRepository = sushiRepository;
    }

    @GetMapping(path="/sushi/recent", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<SushiModel>> recentSushi(){
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());

        List<Sushi> sushiList = sushiRepository.findAll(page).getContent();
        CollectionModel<SushiModel> recentModels = new SushiModelAssembler().toCollectionModel(sushiList);
        recentModels.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RecentSushiController.class).recentSushi()).withRel("recents"));
        return new ResponseEntity<>(recentModels, HttpStatus.OK);
    }

    @Bean
    public RepresentationModelProcessor<PagedModel<EntityModel<Sushi>>> sushiProcessor(EntityLinks links){
        return new RepresentationModelProcessor<>() {
            @Override
            public PagedModel<EntityModel<Sushi>> process(PagedModel<EntityModel<Sushi>> model) {
                model.add(links.linkFor(Sushi.class).slash("recent").withRel("recents"));
                return model;
            }
        };
    }
}
