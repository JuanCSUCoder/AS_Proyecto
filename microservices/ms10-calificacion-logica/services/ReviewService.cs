using System.Collections.Generic;
using ms11.Model;
using System.Net.Http.Json;

namespace ms11.services
{
    public class ReviewService : IReviewService
    {
        private readonly HttpClient _httpClient;

        public ReviewService(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<List<Review>> GetReviewByIdAsync(string id)
        {
            HttpResponseMessage response = await _httpClient.GetAsync($"/scores?prodId={id}");
            response.EnsureSuccessStatusCode();

            var reviews = await response.Content.ReadFromJsonAsync<List<Review>>();
            
            return reviews ?? new List<Review>();
        }

        public async Task<Review> AddReviewAsync(Review review)
        {

            HttpResponseMessage response = await _httpClient.PutAsJsonAsync("/score",review);
            response.EnsureSuccessStatusCode();
            var reviews = await response.Content.ReadFromJsonAsync<Review>();
            
            
            return reviews ;
        }

    }
}