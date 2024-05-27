using ms11.Model;
using ms11.services;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddHttpClient<IReviewService, ReviewService>(client =>
{
    client.BaseAddress = new Uri("http://localhost:5013/"); 
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.MapGet("/reviews/{productId}", async (string productId, IReviewService reviewService) =>
{
    var reviews = await reviewService.GetReviewByIdAsync(productId);
    return reviews != null ? Results.Ok(reviews) : Results.NotFound();
})
.WithName("GetReviewsByProductId")
.WithOpenApi();

app.MapPost("/reviews", async (Review review, IReviewService reviewService) =>
{
    review.id = Guid.NewGuid().ToString();
    var createdReview = await reviewService.AddReviewAsync(review);
    return Results.Created($"/reviews/{createdReview.id}", createdReview);
})
.WithName("CreateReview")
.WithOpenApi();

app.Run();

