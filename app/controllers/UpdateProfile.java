package controllers;

import play.Logger;
import play.mvc.Controller;
import models.*;

import java.util.List;

/**
 * Created by Coady on 17/05/2017.
 */
public class UpdateProfile extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Member Profile");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessments;
    render("updateprofile.html", member, assessments);
  }

  public static void setName(String name)
  {
    Member member = Accounts.getLoggedInMember();
    member.setName(name);
    member.save();
    Logger.info("Updating Member Name: " + name);
    redirect("/updateprofile");
  }

  public static void setEmail(String email)
  {
    Member member = Accounts.getLoggedInMember();
    member.setEmail(email);
    member.save();
    Logger.info("Updating Member Email: " + email);
    redirect("/updateprofile");
  }

  public static void setPassword(String password)
  {
    Member member = Accounts.getLoggedInMember();
    member.setPassword(password);
    member.save();
    Logger.info("Updating Member Password: " + password);
    redirect("/updateprofile");
  }

  public static void setAddress(String address)
  {
    Member member = Accounts.getLoggedInMember();
    member.setAddress(address);
    member.save();
    Logger.info("Updating Member Address: " + address);
    redirect("/updateprofile");
  }

  public static void setGender(String gender)
  {
    Member member = Accounts.getLoggedInMember();
    member.setGender(gender);
    member.save();
    Logger.info("Updating Member Gender: " + gender);
    redirect("/updateprofile");
  }

  public static void setHeight(double height)
  {
    Member member = Accounts.getLoggedInMember();
    member.setHeight(height);
    member.save();
    Logger.info("Updating Member Height: " + height);
    redirect("/updateprofile");
  }

  public static void setWeight(double weight)
  {
    Member member = Accounts.getLoggedInMember();
    member.setWeight(weight);
    member.save();
    Logger.info("Updating Member Weight: " + weight);
    redirect("/updateprofile");
  }
}
