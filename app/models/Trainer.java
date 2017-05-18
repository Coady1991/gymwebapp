package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * The Trainer class contains information on parameters needed to create a Trainer.
 */

@Entity
public class Trainer extends Model
{
  public String name;
  public String email;
  public String password;
  public String address;
  public String gender;

  @OneToMany (cascade = CascadeType.ALL)
  public List<Member> members = new ArrayList<Member>();

  /**
   * The parameters a needed for a Trainer.
   *
   * @param name The Trainers name.
   * @param email The Trainers email.
   * @param password The Trainers password.
   * @param address The Trainers address.
   * @param gender The Trainers gender.
   */
  public Trainer(String name, String email, String password, String address, String gender)
  {
    this.name = name;
    this.email = email;
    this.password = password;
    this.address = address;
    this.gender = gender;
  }

  /**
   * Find a member by email.
   *
   * @param email The Trainers email.
   *
   * @return The member attached to that email.
   */
  public static Trainer findByEmail(String email)
  {
    return find("email", email).first();
  }

  /**
   * Checks password corresponds with email.
   *
   * @param password The Trainers password.
   *
   * @return The password for the email equals password entered.
   */
  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }
}
