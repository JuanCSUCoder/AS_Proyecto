using System.Collections.Generic;

namespace ms11.Model
{
    public class Inventory
    {
        public string Id { get; set; }
        public string Location { get; set; }
        public int Stock { get; set; }
        public Product Product { get; set; }
    }
}