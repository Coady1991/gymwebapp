package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

/**
 * This class controls the accounts for the gymapp.
 */
public class Accounts extends Controller
{
  /**
   * Renders the signup page.
   */
  public static void signup()
  {
    render("signup.html");
  }

  /**
   * Renders the login page.
   */
  public static void login()
  {
    render("login.html");
  }

  /**
   * These are the parameters a user must enter to register as a user of the gymapp.
   *
   * @param name The users name.
   * @param email The users email.
   * @param password The users password.
   * @param address The users address.
   * @param gender The users gender.
   * @param height The users height.
   * @param weight The users weight.
   */
  public static void register(String name, String email, String password, String address, String gender, double height, double weight)
  {
    Logger.info("Registering new user " + email);
    Member member = new Member(name, email, password, address, gender, height, weight);
    member.save();
    redirect("/");
  }

  /**
   * This method authenticates a member or a trainer as a log in.
   * If it fails they are redirectd to they login page again,
   * otherwise they are directed to their respective dashboards.
   * @param email The users email.
   * @param password The users password.
   */
  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    Member member = Member.findByEmail(email);
    Trainer trainer = Trainer.findByEmail(email);
    if ((member != null) && (member.checkPassword(password) == true))
    {
      Logger.info("Authentication successful");
      session.put("logged_in_Memberid", member.id);
      redirect("/dashboard");
    }
    else if((trainer != null) && (trainer.checkPassword(password) == true))
    {
      Logger.info("Authentication successful");
      session.put("logged_in_Trainerid", trainer.id);
      redirect("/trainerdashboard");
    }
    else
    {
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  /**
   * Renders the homepage when a user logouts.
   */
  public static void logout()
  {
    session.clear();
    redirect ("/");
  }

  /**
   * Returns the id of the member currently logged in.
   *
   * @return member id
   */
  public static Member getLoggedInMember()
  {
    Member member = null;
    if (session.contains("logged_in_Memberid"))
    {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    }
    else
    {
      login();
    }
    return member;
  }

  /**
   * Returns the id of the trainer currently logged in.
   *
   * @return trainer id
   */
  public static Trainer getLoggedInTrainer()
  {
    Trainer trainer = null;
    if(session.contains("logged_in_Trainerid"))
    {
      String trainerId = session.get("logged_in_Trainerid");
      trainer = Trainer.findById(Long.parseLong(trainerId));
    }
    else
    {
      login();
    }
    return trainer;
  }
}