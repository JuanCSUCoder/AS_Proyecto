package com.usuarios.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"UserProfile\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  
  private String name;
  private String email;
  
  @Embedded
  private Location location;

  @Embeddable
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Location {
    @Embedded
    private Description description;
    
    @Embedded
    private Coordinates coordinates;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Description {
      private String address;
      private String city;
      private String countryCode;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Coordinates {
      private double lat;
      private double lng;
    }
  }
}
