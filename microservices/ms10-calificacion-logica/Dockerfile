# Use the official .NET runtime as a base image
FROM mcr.microsoft.com/dotnet/sdk:8.0 
WORKDIR /app
EXPOSE 5265
COPY ms11-calificacion-logica.csproj ./
RUN dotnet restore
COPY . .
CMD dotnet watch --urls "http://0.0.0.0:5265"

