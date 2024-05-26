package co.edu.javeriana;

import java.util.List;

import co.edu.javeriana.model.Review;
import co.edu.javeriana.repositories.ReviewRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class ReviewsResource {

    @Inject
    ReviewRepository repo;

    @GET
    @Path("/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getScores(@QueryParam("prodId") String prodId) {
        List<Review> reviews = repo.findByProductId(prodId);
        return reviews;
    }
}
