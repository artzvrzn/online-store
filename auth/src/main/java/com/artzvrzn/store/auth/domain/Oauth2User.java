package com.artzvrzn.store.auth.domain;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Oauth2User implements UserDetails {
  private UUID id;
  private String username;
  private String firstName;
  private String lastName;
  private String password;
  private Status status;
  private String email;
  private String phone;
  private String country;
  private String city;
  private String streetName;
  private String house;
  private String apartment;
  private Collection<? extends GrantedAuthority> authorities;

  public Oauth2User(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.password = user.getPassword();
    this.status = user.getStatus();
    this.email = user.getEmail();
    this.phone = user.getPhone();
    this.country = user.getCountry();
    this.city = user.getCity();
    this.streetName = user.getStreetName();
    this.house = user.getHouse();
    this.apartment = user.getApartment();
    this.authorities = user.getRoles()
        .stream()
        .map(r -> new SimpleGrantedAuthority(r.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
//    return Status.ACTIVE.equals(status);
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public UUID getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Status getStatus() {
    return status;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getStreetName() {
    return streetName;
  }

  public String getHouse() {
    return house;
  }

  public String getApartment() {
    return apartment;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public void setHouse(String house) {
    this.house = house;
  }

  public void setApartment(String apartment) {
    this.apartment = apartment;
  }

  public void setAuthorities(
      Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }
}
