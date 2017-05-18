package models;

import play.db.jpa.Model;
import utils.Analytics;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * The Member class contains information on parameters needed to create a member.
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

  /**
   * The parameters a user must enter before they become a Member.
   *
   * @param name The users name.
   * @param email The users email.
   * @param password The users password.
   * @param address The users address.
   * @param gender The users gender.
   * @param height The users height.
   * @param weight The users weight.
   */
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

  /**
   * Find a member by email.
   *
   * @param email The Members email.
   *
   * @return The member attached to that email.
   */
  public static Member findByEmail(String email)
  {
    return find("email", email).first();
  }

  /**
   * Checks password corresponds with email.
   *
   * @param password The Members password.
   *
   * @return The password for the email equals password entered.
   */
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

  /**
   * Updates the Members name field.
   *
   * @param name The Members name.
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Updates the Members email field.
   *
   * @param email The Members email.
   */
  public void setEmail(String email)
  {
    this.email = email;
  }

  /**
   * Updates the Members password field.
   *
   * @param password The Members field.
   */
  public void setPassword(String password)
  {
    this.password = password;
  }

  /**
   * Updates the Members address field.
   *
   * @param address The Members address.
   */
  public void setAddress(String address)
  {
    this.address = address;
  }

  /**
   * Updates the Members gender field.
   *
   * @param gender The Members gender.
   */
  public void setGender(String gender)
  {
    this.gender = gender;
  }

  /**
   * Updates the Members height field.
   *
   * @param height The Members height
   */
  public void setHeight(double height)
  {
    this.height = height;
  }

  /**
   * Updates the Members weight field.
   *
   * @param weight The Members weight.
   */
  public void setWeight(double weight)
  {
    this.weight = weight;
  }

  /**
   * Returns the Members BMI for use in the trainers view of the Member.
   *
   * @return The Members BMI.
   */
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

  /**
   * Returns the Members BMI Category for use in the trainers view of the Member.
   *
   * @return The Members BMI Category.
   */
  public String getCategory()
  {
    return Analytics.determineBMICategory(getBMI());
  }

  /**
   * Returns a colour for the Member indicating Ideal Weight fo use in the trainers view of the Member.
   * @return
   */
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