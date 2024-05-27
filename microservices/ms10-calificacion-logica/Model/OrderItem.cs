using System.Collections.Generic;

namespace ms11.Model
{
    public class OrderItem
    {
        public string Id { get; set; }
        public int Quantity { get; set; }
        public Order Order { get; set; }
        public Product Product { get; set; }
    }
}