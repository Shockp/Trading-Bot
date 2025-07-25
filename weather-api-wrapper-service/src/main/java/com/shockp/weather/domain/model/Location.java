package com.shockp.weather.domain.model;

import java.util.Objects;

/**
 * Represents a geographical location with coordinates and place information.
 * 
 * <p>This class encapsulates location data including latitude, longitude, city, and country.
 * It provides validation for coordinate ranges and ensures data integrity.</p>
 * 
 * <p>Instances of this class are immutable and thread-safe.</p>
 * 
 * @author Weather API Wrapper Service
 * @version 1.0
 * @since 1.0
 */
public final class Location {
    
    /** Minimum valid latitude value. */
    private static final double MIN_LATITUDE = -90.0;
    
    /** Maximum valid latitude value. */
    private static final double MAX_LATITUDE = 90.0;
    
    /** Minimum valid longitude value. */
    private static final double MIN_LONGITUDE = -180.0;
    
    /** Maximum valid longitude value. */
    private static final double MAX_LONGITUDE = 180.0;
    
    /** The latitude coordinate in decimal degrees. */
    private final double latitude;
    
    /** The longitude coordinate in decimal degrees. */
    private final double longitude;
    
    /** The city name. */
    private final String city;
    
    /** The country name. */
    private final String country;

    /**
     * Constructs a new {@link Location} instance with the specified coordinates and place information.
     *
     * <p>Validates that:
     * <ul>
     *   <li>{@code latitude} is between {@code #MIN_LATITUDE} and {@code #MAX_LATITUDE} degrees</li>
     *   <li>{@code longitude} is between {@code #MIN_LONGITUDE} and {@code #MAX_LONGITUDE} degrees</li>
     *   <li>{@code city} is not {@code null} or empty</li>
     *   <li>{@code country} is not {@code null} or empty</li>
     * </ul>
     * Validation is performed by {@link #validateCoordinates(double, double)} and
     * {@link #validatePlaceNames(String, String)}.
     * </p>
     *
     * @param latitude the latitude coordinate in decimal degrees; must be between {@code -90.0} and {@code 90.0}
     * @param longitude the longitude coordinate in decimal degrees; must be between {@code -180.0} and {@code 180.0}
     * @param city the city name; must not be {@code null} or empty
     * @param country the country name; must not be {@code null} or empty
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Location(double latitude, double longitude, String city, String country) {
        validateCoordinates(latitude, longitude);
        validatePlaceNames(city, country);
        
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city.trim();
        this.country = country.trim();
    }

    /**
     * Returns the latitude coordinate in decimal degrees.
     * 
     * @return the latitude value between {@code #MIN_LATITUDE} and {@code #MAX_LATITUDE}
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude coordinate in decimal degrees.
     * 
     * @return the longitude value between {@code #MIN_LONGITUDE} and {@code #MAX_LONGITUDE}
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns the city name.
     * 
     * @return the city name, never {@code null} or empty
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the country name.
     * 
     * @return the country name, never {@code null} or empty
     */
    public String getCountry() {
        return country;
    }

    /**
     * Validates coordinate values are within valid ranges.
     * 
     * @param latitude the latitude to validate
     * @param longitude the longitude to validate
     * @throws IllegalArgumentException if coordinates are out of valid ranges
     */
    private static void validateCoordinates(double latitude, double longitude) {
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
            throw new IllegalArgumentException(
                String.format("Latitude must be between %.1f and %.1f degrees, got: %.6f", 
                    MIN_LATITUDE, MAX_LATITUDE, latitude));
        }

        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
            throw new IllegalArgumentException(
                String.format("Longitude must be between %.1f and %.1f degrees, got: %.6f", 
                    MIN_LONGITUDE, MAX_LONGITUDE, longitude));
        }
    }

    /**
     * Validates that place names are not {@code null} or empty.
     * 
     * @param city the city name to validate
     * @param country the country name to validate
     * @throws IllegalArgumentException if any name is {@code null} or empty
     */
    private static void validatePlaceNames(String city, String country) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }

        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
    }

    @Override
    public String toString() {
        return String.format("Location{latitude=%.6f, longitude=%.6f, city='%s', country='%s'}", 
            latitude, longitude, city, country);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Location other)) {
            return false;
        }
        return Double.compare(latitude, other.latitude) == 0 &&
               Double.compare(longitude, other.longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
} 