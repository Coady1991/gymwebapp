package models;

import play.db.jpa.Model;
import utils.Analytics;

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
   * Returns the Members name.
   *
   * @return The Members name.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Returns the Members email.
   *
   * @return The Members email
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * Returns the Members password.
   *
   * @return The Members password.
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Returns the Members address..
   *
   * @return The Members address
   */
  public String getAddress()
  {
    return address;
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

  //***************************************************************************************
  // Setters for Member
  //***************************************************************************************


  public void setName(String name)
  {
    this.name = name;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public void setGender(String gender)
  {
    this.gender = gender;
  }

  public void setHeight(double height)
  {
    this.height = height;
  }

  public void setWeight(double weight)
  {
    this.weight = weight;
  }

  public double getBMI()
  {
    if (assessments.size() > 0)
    {
      return Analytics.toTwoDecimalPlaces(assessments.get(assessments.size() - 1).getWeight() / (getHeight() * getHeight()));
    }
    else
    {
      return Analytics.toTwoDecimalPlaces(getWeight() / (getHeight() * getHeight()));
    }
  }

  public String getCategory()
  {
    return Analytics.determineBMICategory(getBMI());
  }

  public String getIdeal()
  {
    double ideal = 0.0;
    double heightInInches = Analytics.convertHeightMetresToInches(getHeight());

    if (assessments.size() > 0)
    {
      weight = assessments.get(assessments.size() - 1).getWeight();
    }
    else
    {
      weight = getWeight();
    }

    if(getHeight() <= 1.52)
    {
      if(getGender().equals("Male"))
      {
        ideal = 45.5;
      }
      else
      {
        ideal = 50;
      }
    }

    if(getHeight() >= 1.52)
    {
      if(getGender().equals("Male"))
      {
        ideal = 50 + (2.3 * (heightInInches - 60));
      }
      else
      {
        ideal = 45.5 + (2.3 * (heightInInches - 60));
      }
    }

    if ((weight >= (ideal - 2)) && (weight <= (ideal + 2)))
    {
      return "green";
    }
    else
    {
      return "red";
    }
  }

}