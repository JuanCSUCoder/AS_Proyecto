
using ms11.Model;

namespace ms11.services
{
    public interface IReviewService
    {

        Task<List<Review>> GetReviewByIdAsync(string id);
        Task<Review> AddReviewAsync(Review review);

    }
}