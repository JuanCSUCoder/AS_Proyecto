using System.Collections.Generic;

namespace ms11.Model
{
    public class Order
    {
        public string Id { get; set; }
        public string Status { get; set; }
        public double Total { get; set; }
        public User User { get; set; }
        public List<OrderItem> Items { get; set; }
    }
}