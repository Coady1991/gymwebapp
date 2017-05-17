package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coady on 25/04/2017.
 */

@Entity
public class Member extends Model
{
  public String name;
  public String email;
  public String password;
  public String address;
  public String gender;
  public double height;
  public double weight;

  @OneToMany (cascade = CascadeType.ALL)
  public List<Assessment> assessments = new ArrayList<Assessment>();

  public Member(String name, String email, String password, String address, String gender, double height, double weight)
  {
    this.name = name;
    this.email = email;
    this.password = password;
    this.address = address;
    this.gender = gender;
    this.height = height;
    this.weight = weight;

  }

  public static Member findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }

  //***************************************************************************************
  // Getters for Member
  //***************************************************************************************

  /**
   * Returns the Members height(m).
   *
   * @return The Members height(m).
   */

  public double getHeight()
  {
    return height;
  }

  /**
   * Returns the Members starting weight(kg).
   *
   * @return The Members starting weight(kg).
   */

  public double getWeight()
  {
    return weight;
  }

  /**
   * Returns the Members gender.
   *
   * @return The Members gender.
   */

  public String getGender()
  {
    return gender;
  }
}