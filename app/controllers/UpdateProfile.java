package controllers;

import play.Logger;
import play.mvc.Controller;
import models.*;

import java.util.List;

/**
 * This class allows a member to update their details
 */
public class UpdateProfile extends Controller
{
  /**
   * This method renders the update profile page.
   */
  public static void index()
  {
    Logger.info("Rendering Member Profile");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessments;
    render("updateprofile.html", member, assessments);
  }

  /**
   * Allows the member to update their name.
   *
   * @param name The members name.
   */
  public static void setName(String name)
  {
    Member member = Accounts.getLoggedInMember();
    member.setName(name);
    member.save();
    Logger.info("Updating Member Name: " + name);
    redirect("/updateprofile");
  }

  /**
   * Allows the member to update their email.
   *
   * @param email The members email.
   */
  public static void setEmail(String email)
  {
    Member member = Accounts.getLoggedInMember();
    member.setEmail(email);
    member.save();
    Logger.info("Updating Member Email: " + email);
    redirect("/updateprofile");
  }

  /**
   * Allows the member to update their password.
   *
   * @param password The members password.
   */
  public static void setPassword(String password)
  {
    Member member = Accounts.getLoggedInMember();
    member.setPassword(password);
    member.save();
    Logger.info("Updating Member Password: " + password);
    redirect("/updateprofile");
  }

  /**
   * Allows the member to update their address.
   *
   * @param address The members address.
   */
  public static void setAddress(String address)
  {
    Member member = Accounts.getLoggedInMember();
    member.setAddress(address);
    member.save();
    Logger.info("Updating Member Address: " + address);
    redirect("/updateprofile");
  }

  /**
   * Allows the member to update their gender.
   *
   * @param gender The members gender.
   */
  public static void setGender(String gender)
  {
    Member member = Accounts.getLoggedInMember();
    member.setGender(gender);
    member.save();
    Logger.info("Updating Member Gender: " + gender);
    redirect("/updateprofile");
  }

  /**
   * Allows the member to update their height.
   *
   * @param height The members height.
   */
  public static void setHeight(double height)
  {
    Member member = Accounts.getLoggedInMember();
    member.setHeight(height);
    member.save();
    Logger.info("Updating Member Height: " + height);
    redirect("/updateprofile");
  }

  /**
   * Allows the member to update their weight.
   *
   * @param weight The members weight.
   */
  public static void setWeight(double weight)
  {
    Member member = Accounts.getLoggedInMember();
    member.setWeight(weight);
    member.save();
    Logger.info("Updating Member Weight: " + weight);
    redirect("/updateprofile");
  }
}
