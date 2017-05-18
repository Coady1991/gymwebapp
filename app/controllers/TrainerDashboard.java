package controllers;

import play.Logger;
import play.mvc.Controller;
import models.*;
import java.util.List;


/**
 * The class controls the methods that can be used by a trainer
 * in their dashboard.
 */
public class TrainerDashboard extends Controller
{
  /**
   * When a trainer logs in this method renders their dashboard
   * and associated details for that trainer.
   */
  public static void index()
  {
    Logger.info("Rendering TrainerDashboard");
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> members = trainer.members;
    render("trainerdashboard.html", trainer, members);
  }

  /**
   * This allows a trainer delete a member by member id.
   *
   * @param memberid The members id.
   */
  public static void deleteMember(Long memberid)
  {
    Trainer trainer = Accounts.getLoggedInTrainer();
    Member member = Member.findById(memberid);
    trainer.members.remove(member);
    trainer.save();
    member.delete();
    Logger.info("Member deleted");
    redirect("/trainerdashboard");
  }

  /**
   * This method renders the view for the trainer of a specific member.
   *
   * @param memberid The members id.
   */
  public static void memberDashboard(Long memberid)
  {
    Logger.info("Rendering Dashboard");
    Trainer trainer = Accounts.getLoggedInTrainer();
    Member member = Member.findById(memberid);
    List<Assessment> assessments = member.assessments;
    render("trainerview.html", trainer, member, assessments);
  }

  /**
   * This method allows the trainer to add a comment to a members assessment.
   *
   * @param memberid The members id.
   * @param assessmentid The assessment id.
   * @param comment The commentn the trainer makes.
   */
  public static void addComment(Long memberid, Long assessmentid, String comment)
  {
    Trainer trainer = Accounts.getLoggedInTrainer();
    Member member = Member.findById(memberid);
    List<Assessment> assessments = member.assessments;
    Assessment assessment = Assessment.findById(assessmentid);
    assessment.setComment(comment);
    assessment.save();
    Logger.info("Updating assessment comment");
    render("trainerview.html", trainer, member, assessments);
  }
}
