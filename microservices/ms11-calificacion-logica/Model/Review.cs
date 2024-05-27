using System.Collections.Generic;

namespace ms11.Model
{
    public class Review
    {
        public required string id { get; set; }
        public int score { get; set; }
        public required Product product { get; set; }
        public required User user { get; set; }
    }
}