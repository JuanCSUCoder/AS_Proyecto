package co.edu.javeriana;

import java.util.ArrayList;
import java.util.List;

import co.edu.javeriana.model.Review;
import co.edu.javeriana.repositories.ProductRepository;
import co.edu.javeriana.repositories.ReviewRepository;
import co.edu.javeriana.repositories.UserRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class ReviewsResource {

    @Inject
    ReviewRepository repo;

    @Inject
    ProductRepository prodrepo;

    @Inject
    UserRepository userrepo;

    @GET
    @Path("/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getScores(@DefaultValue("undefined") @QueryParam("prodId") String prodId) {
        if (prodId.equals("undefined")) {
            List<Review> revs = new ArrayList<>();
            repo.findAll().iterator().forEachRemaining(revs::add);
            return revs;
        } else {
            List<Review> reviews = repo.findByProductId(prodId);
            return reviews;
        }
    }

    @PUT
    @Path("/score")
    @Produces(MediaType.APPLICATION_JSON)
    public Review putScore(Review review) {
        review.setProduct(prodrepo.findById(review.getProduct().getId()).get());
        review.setUser(userrepo.findById(review.getUser().getId()).get());
        return repo.save(review);
    }
}
