using System.Collections.Generic;

namespace ms11.Model
{

    public class Product
    {
        public required string id { get; set; }
        public string name { get; set; }
        public string descr { get; set; }
        public string imageURL { get; set; }
        public double price { get; set; }
    }

}