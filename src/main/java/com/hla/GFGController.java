package com.hla;

import java.util.logging.Logger;
import org.bson.Document;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class GFGController {

    private final static Logger LOGGER = Logger.getLogger(GFGController.class.getName());
    private final MongoTemplate mongoTemplate;
    private final RestHighLevelClient elasticClient;

    public GFGController(MongoTemplate mongoTemplate, RestHighLevelClient elasticClient) {
        this.mongoTemplate = mongoTemplate;
        this.elasticClient = elasticClient;
    }

    @GetMapping("/")
    public String pingMongoAndElasticSearch() {
        var response = new StringBuilder();
        pingMongo(response);
        pingElastic(response);
        LOGGER.info("Get request. Response: " + response);
        return response.toString();
    }

    private void pingMongo(StringBuilder response) {
        try {
            mongoTemplate.getDb().runCommand(new Document("ping", 1));
            response.append("MongoDB is requested. ");
        } catch (Exception e) {
            response.append("MongoDB failed to request. ");
        }
    }

    private void pingElastic(StringBuilder response) {
        try {
            MainResponse mainResponse = elasticClient.info(RequestOptions.DEFAULT);
            if (mainResponse != null) {
                response.append("Elasticsearch is requested. ");
            } else {
                response.append("Elasticsearch is down. ");
            }
        } catch (Exception e) {
            response.append("Elasticsearch failed to request. ");
        }
    }
}